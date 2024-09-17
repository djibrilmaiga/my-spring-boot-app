package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    // Trouver un utilisateur par son id
    public Utilisateur findByLogin(String login);
}
