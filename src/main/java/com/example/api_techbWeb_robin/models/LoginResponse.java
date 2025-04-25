package com.example.api_techbWeb_robin.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private UserInfo infosUser;
    private Long roleId;

    public LoginResponse(String token, UserInfo infosUser, Long roleId) {
        this.token = token;
        this.infosUser = infosUser;
        this.roleId = roleId;
    }

    @Getter
    @Setter
    public static class UserInfo {
        private Long id;
        private Long userId;
        private String prenom;
        private String nom;
        private String email;
        private String role;
        private String telephone;
        private String adresse;
        private String photoProfil;
        private String description;

        public UserInfo(
                Long id,
                Long userId,
                String prenom,
                String nom,
                String email,
                String role,
                String telephone,
                String description,
                String adresse,
                String photoProfil
        ) {
            this.id = id;
            this.userId = userId;
            this.prenom = prenom;
            this.nom = nom;
            this.email = email;
            this.role = role;
            this.telephone = telephone;
            this.description = description;
            this.adresse = adresse;
            this.photoProfil = photoProfil;
        }
    }
}
