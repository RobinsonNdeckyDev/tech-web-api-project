package com.example.api_techbWeb_robin.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User extends Auditable implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String photoProfil;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getNomRole()));
    }

    @Override
    public String getUsername() { return email; }
    @Override
    public boolean isAccountNonExpired()     { return true; }
    @Override
    public boolean isAccountNonLocked()      { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled()               { return true; }
}

