package com.example.api_techbWeb_robin.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "document")
@Data
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String cheminFichier;

    @ManyToOne
    @JoinColumn(name="cree_par_id")
    private User createur;

    private LocalDateTime dateCreation;

    @ManyToOne
    @JoinColumn(name="visibilite_role")
    private Role visibiliteRole;
}
