package com.example.api_techbWeb_robin.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "partenaire")
@Data
@NoArgsConstructor
public class Partenaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String type;

    @ManyToMany
    @JoinTable(name="partenariat_formation",
            joinColumns=@JoinColumn(name="partenaire_id"),
            inverseJoinColumns=@JoinColumn(name="formation_id"))
    private Set<Formation> formations;
}
