// AuthController.java
package com.example.api_techbWeb_robin.controllers;

import com.example.api_techbWeb_robin.models.LoginRequest;
import com.example.api_techbWeb_robin.models.LoginResponse;
import com.example.api_techbWeb_robin.models.RegisterRequest;
import com.example.api_techbWeb_robin.models.User;
import com.example.api_techbWeb_robin.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            logger.warn("Login failed for {}: {}", request.getEmail(), ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User created = authService.register(request);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(created);
        } catch (RuntimeException ex) {
            logger.warn("Registration failed for {}: {}", request.getEmail(), ex.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", ex.getMessage()));
        }
    }
}
