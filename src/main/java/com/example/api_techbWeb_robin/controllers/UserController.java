package com.example.api_techbWeb_robin.controllers;

import com.example.api_techbWeb_robin.dtos.UserRequestDTO;
import com.example.api_techbWeb_robin.models.RegisterRequest;
import com.example.api_techbWeb_robin.models.User;
import com.example.api_techbWeb_robin.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Liste des utilisateurs
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    // Détails d'un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Enregistrement d'un utilisateur
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            Long roleId = registerRequest.getRoleId();  // Pas de conversion nécessaire

            // Appeler le service avec l'ID du rôle
            User user = userService.createUser(
                    registerRequest.getEmail(),
                    registerRequest.getPassword(),
                    registerRequest.getNom(),
                    registerRequest.getPrenom(),
                    roleId,  // Utiliser le roleId directement en tant que Long
                    registerRequest.getTelephone(),
                    registerRequest.getAdresse(),
                    registerRequest.getDescription(),
                    registerRequest.getPhotoProfil()
            );
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    // Mise à jour d'un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userDetails) {
        try {
            Optional<User> updatedUser = userService.updateUser(id, userDetails);
            return updatedUser.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("error", "Erreur interne."));
        }
    }

    // Suppression d'un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
