package com.example.api_techbWeb_robin.config;

import com.example.api_techbWeb_robin.models.Role;
import com.example.api_techbWeb_robin.models.User;
import com.example.api_techbWeb_robin.repository.RoleRepository;
import com.example.api_techbWeb_robin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.superadmin.email}")
    private String adminEmail;

    @Value("${app.superadmin.password}")
    private String adminPassword;

    @Value("${app.superadmin.prenom}")
    private String adminPrenom;

    @Value("${app.superadmin.nom}")
    private String adminNom;

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // 1) Créer le rôle SUPER_ADMIN s'il n'existe pas
        Role superAdminRole = roleRepository
                .findByNomRole("SUPER_ADMIN")
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setNomRole("SUPER_ADMIN");
                    return roleRepository.save(r);
                });

        // 2) Créer l'utilisateur super-admin si absent
        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = new User();
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setPrenom(adminPrenom);
            admin.setNom(adminNom);
            admin.setTelephone("N/A");
            admin.setAdresse("N/A");
            admin.setDescription("Compte Super Admin par défaut");
            admin.setPhotoProfil("default.png");
            admin.setRole(superAdminRole);
            userRepository.save(admin);
            System.out.println("✅ Super-admin créé : " + adminEmail);
        }
    }

}
