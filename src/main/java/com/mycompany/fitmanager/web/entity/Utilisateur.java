package com.mycompany.fitmanager.web.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor @Entity
public class Utilisateur implements UserDetails {
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Cette propriété est AUTO INCREMENT dans la Database
    @Column(name = "utilisateur_id") // Nom du champ dans la database
    private Integer id;

    @Column(unique = true, nullable = false) //  Champ dans la database est UNIQUE et NOT NULL
    private String login;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // le champ est un ENUM dans la database
    @Column(nullable = false)
    private RoleUser role;

    // Classe Enum RoleUser
    public enum RoleUser {
        ADMIN, MANAGER;
    }

    // Méthodes
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() { // Méthode pour renvoyer le login
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}