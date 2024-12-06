package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    // SQL Query : Select * from Utilisateur Where login = ?
    Optional<Utilisateur> findByLogin(String login);
}