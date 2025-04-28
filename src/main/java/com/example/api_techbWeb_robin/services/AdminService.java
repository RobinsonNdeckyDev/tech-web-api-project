package com.example.api_techbWeb_robin.services;

import com.example.api_techbWeb_robin.dtos.AdminDTO;
import com.example.api_techbWeb_robin.models.Administrateur;
import com.example.api_techbWeb_robin.models.User;
import com.example.api_techbWeb_robin.repository.AdminRepository;
import com.example.api_techbWeb_robin.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminService {
    private final AdminRepository adminRepo;
    private final UserRepository userRepo;

    public AdminService(AdminRepository adminRepo, UserRepository userRepo) {
        this.adminRepo = adminRepo;
        this.userRepo = userRepo;
    }

    private AdminDTO toDto(Administrateur admin) {
        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setUserId(admin.getUser().getId());
        dto.setService(admin.getService());
        return dto;
    }

    private Administrateur toEntity(AdminDTO dto) {
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Administrateur admin = new Administrateur();
        admin.setUser(user);
        admin.setService(dto.getService());
        return admin;
    }

    public List<AdminDTO> getAllAdmins() {
        return adminRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public AdminDTO getAdminById(Long id) {
        Administrateur admin = adminRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        return toDto(admin);
    }

    public AdminDTO createAdmin(AdminDTO dto) {
        Administrateur admin = toEntity(dto);
        Administrateur saved = adminRepo.save(admin);
        return toDto(saved);
    }

    public AdminDTO updateAdmin(Long id, AdminDTO dto) {
        Administrateur existing = adminRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        // si on veut permettre le changement d’utilisateur :
        if (!existing.getUser().getId().equals(dto.getUserId())) {
            User newUser = userRepo.findById(dto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            existing.setUser(newUser);
        }
        existing.setService(dto.getService());
        return toDto(adminRepo.save(existing));
    }

    public void deleteAdmin(Long id) {
        if (!adminRepo.existsById(id)) {
            throw new EntityNotFoundException("Admin not found");
        }
        adminRepo.deleteById(id);
    }
}
