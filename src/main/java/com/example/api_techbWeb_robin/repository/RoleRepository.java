package com.example.api_techbWeb_robin.repository;

import com.example.api_techbWeb_robin.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Correspond à la propriété nomRole de votre entité
    Optional<Role> findByNomRole(String nomRole);
    boolean existsByNomRole(String nomRole);

}
