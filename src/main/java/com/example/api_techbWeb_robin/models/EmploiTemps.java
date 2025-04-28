package com.example.api_techbWeb_robin.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Entity
@Table(name = "emploi_temps")
@Data
@NoArgsConstructor
public class EmploiTemps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="formation_id")
    private Formation formation;

    @Column(nullable = false)
    private String jour;

    @Column(nullable = false)
    private LocalTime heureDebut;

    @Column(nullable = false)
    private LocalTime heureFin;

    @Column(nullable = false)
    private String intituleCours;

    @ManyToOne @JoinColumn(name="formateur_id")
    private Formateur formateur;
}
