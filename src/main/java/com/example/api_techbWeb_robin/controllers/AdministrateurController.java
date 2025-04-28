package com.example.api_techbWeb_robin.controllers;

import com.example.api_techbWeb_robin.dtos.AdminDTO;
import com.example.api_techbWeb_robin.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdministrateurController {

    private final AdminService adminService;

    public AdministrateurController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<AdminDTO> getAll() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    @PostMapping
    public ResponseEntity<AdminDTO> create(@Valid @RequestBody AdminDTO dto) {
        AdminDTO created = adminService.createAdmin(dto);
        return ResponseEntity
                .status(201)
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody AdminDTO dto) {
        return ResponseEntity.ok(adminService.updateAdmin(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
