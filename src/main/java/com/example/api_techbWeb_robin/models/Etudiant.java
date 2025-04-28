package com.example.api_techbWeb_robin.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "etudiants")
@Data
@NoArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "utilisateur_id")
    private User user;

    @Column(nullable = false)
    private String ine;

    @Column(nullable = false)
    private LocalDate dateNaissance;

    @ManyToOne
    @JoinColumn(name="formation_id")
    private Formation formation;

    @Column(nullable = false)
    private String promo;

    @Column(nullable = false)
    private Integer anneeDebut;

    @Column(nullable = false)
    private Integer anneeSortie;
}
