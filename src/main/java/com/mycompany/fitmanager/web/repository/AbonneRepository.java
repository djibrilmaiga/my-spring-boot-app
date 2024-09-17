package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Abonne;
import com.mycompany.fitmanager.web.entity.enums.Genre;
import com.mycompany.fitmanager.web.entity.enums.Statut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AbonneRepository extends JpaRepository<Abonne, Integer> {
    // Rechercher un abonné par son téléphone
    Optional<Abonne> findByTelephone(String telephone);

    // Rechercher les abonnés par genre
    List<Abonne> findByGenre(Genre genre);

    // Rechercher tous les abonnés actifs
    List<Abonne> findByStatutAbonnement(Statut statutAbonnement);

    // Rechercher un abonné par nom et prénom
    List<Abonne> findByNomAndPrenom(String nom, String prenom);
}
