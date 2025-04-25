package com.example.api_techbWeb_robin.services;

import com.example.api_techbWeb_robin.models.Role;
import com.example.api_techbWeb_robin.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Lister tous les rôles
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    // Trouver un rôle par son id
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    // Créer un rôle
    public Role createRole(String nomRole) {
        if (roleRepository.findByNomRole(nomRole).isPresent()) {
            throw new IllegalArgumentException("Ce rôle existe déjà.");
        }
        Role role = new Role();
        role.setNomRole(nomRole);
        return roleRepository.save(role);
    }

    // Mettre à jour un rôle
    public Optional<Role> updateRole(Long id, Role roleDetails) {
        return roleRepository.findById(id).map(role -> {
            role.setNomRole(roleDetails.getNomRole());
            return roleRepository.save(role);
        });
    }

    // Supprimer un rôle
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
