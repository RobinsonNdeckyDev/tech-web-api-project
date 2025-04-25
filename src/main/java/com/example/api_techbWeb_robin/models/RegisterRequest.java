package com.example.api_techbWeb_robin.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String password;
    private String prenom;
    private String nom;
    private String adresse;
    private String telephone;
    private String description;
    private String photoProfil;

    private Long roleId;
}
