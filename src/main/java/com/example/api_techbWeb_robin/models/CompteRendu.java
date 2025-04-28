package com.example.api_techbWeb_robin.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "compte_rendu")
@Data
@NoArgsConstructor
public class CompteRendu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="createur_id")
    private User createur;

    @ManyToMany
    @JoinTable(name="compte_rendu_document",
            joinColumns=@JoinColumn(name="compte_rendu_id"),
            inverseJoinColumns=@JoinColumn(name="document_id"))
    private Set<Document> documents = new HashSet<>();
}
