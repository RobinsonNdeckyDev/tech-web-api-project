package com.example.api_techbWeb_robin.services;

import com.example.api_techbWeb_robin.models.Role;
import com.example.api_techbWeb_robin.models.User;
import com.example.api_techbWeb_robin.repository.RoleRepository;
import com.example.api_techbWeb_robin.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Récupérer tous les utilisateurs
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Récupérer un utilisateur par son id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Créer un utilisateur (modifié pour prendre roleId en Long)
    public User createUser(
            String email,
            String password,
            String nom,
            String prenom,
            Long roleId,        // ← on passe l'ID
            String telephone,
            String adresse,
            String description,
            String photoProfil
    ) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("L'utilisateur avec cet email existe déjà.");
        }

        // On récupère le rôle via l'ID
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Rôle invalide (ID=" + roleId + ")"));

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setRole(role);
        user.setTelephone(telephone);
        user.setAdresse(adresse);
        user.setDescription(description);
        user.setPhotoProfil(photoProfil);

        return userRepository.save(user);
    }

    // Modifier un utilisateur
    public Optional<User> updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            if (!user.getEmail().equals(userDetails.getEmail())
                    && userRepository.existsByEmail(userDetails.getEmail())) {
                throw new IllegalArgumentException("Cet email est déjà utilisé par un autre utilisateur.");
            }
            user.setNom(userDetails.getNom());
            user.setPrenom(userDetails.getPrenom());
            user.setEmail(userDetails.getEmail());
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            user.setRole(userDetails.getRole());
            user.setTelephone(userDetails.getTelephone());
            user.setAdresse(userDetails.getAdresse());
            user.setDescription(userDetails.getDescription());
            user.setPhotoProfil(userDetails.getPhotoProfil());
            return userRepository.save(user);
        });
    }

    // Supprimer un utilisateur
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
