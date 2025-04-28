package com.example.api_techbWeb_robin.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "insertion")
@Data
@NoArgsConstructor
public class Insertion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="etudiant_id")
    private Etudiant etudiant;

    @Column(nullable = false)
    private String typeInsertion;

    @Column(nullable = false)
    private String statut;

    @Column(nullable = false)
    private LocalDate dateSuivi;
}
