package com.example.api_techbWeb_robin.controllers;

import com.example.api_techbWeb_robin.models.Role;
import com.example.api_techbWeb_robin.models.RoleRequest;
import com.example.api_techbWeb_robin.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Liste de tous les rôles
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    // Détails d’un rôle par ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return roleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Création d’un rôle
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody @Valid RoleRequest request) {
        Role created = roleService.createRole(request.getNomRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Mise à jour d’un rôle
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
        try {
            Optional<Role> updatedRole = roleService.updateRole(id, roleDetails);
            return updatedRole.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Erreur serveur"));
        }
    }

    // Suppression d’un rôle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
