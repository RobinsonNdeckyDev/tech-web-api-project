// AuthService.java
package com.example.api_techbWeb_robin.services;

import com.example.api_techbWeb_robin.models.LoginRequest;
import com.example.api_techbWeb_robin.models.LoginResponse;
import com.example.api_techbWeb_robin.models.RegisterRequest;
import com.example.api_techbWeb_robin.models.User;
import com.example.api_techbWeb_robin.models.Role;
import com.example.api_techbWeb_robin.repository.UserRepository;
import com.example.api_techbWeb_robin.repository.RoleRepository;
import com.example.api_techbWeb_robin.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Identifiants invalides");
        }

        String token = jwtUtil.generateToken(user);

        // Construction de l'objet UserInfo
        LoginResponse.UserInfo info = new LoginResponse.UserInfo(
                user.getId(),
                user.getId(),
                user.getPrenom(),
                user.getNom(),
                user.getEmail(),
                user.getRole().getNomRole(),
                user.getTelephone(),
                user.getDescription(),
                user.getAdresse(),
                user.getPhotoProfil()
        );

        return new LoginResponse(token, info, user.getRole().getId());
    }

    /**
     * Méthode appelée par le controller pour inscrire un user.
     */
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Un utilisateur avec cet email existe déjà");
        }

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));

        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setPrenom(request.getPrenom());
        newUser.setNom(request.getNom());
        newUser.setAdresse(request.getAdresse());
        newUser.setTelephone(request.getTelephone());
        newUser.setDescription(request.getDescription());
        newUser.setPhotoProfil(request.getPhotoProfil());
        newUser.setRole(role);

        return userRepository.save(newUser);
    }
}
