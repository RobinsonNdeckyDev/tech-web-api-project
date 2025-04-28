package com.example.api_techbWeb_robin.repository;

import com.example.api_techbWeb_robin.models.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Administrateur, Long> {
    Optional<Administrateur> findByUserId(Long userId);
}
