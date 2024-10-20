package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.dto.AbonneSansAbonnementDTO;
import com.mycompany.fitmanager.web.dto.AbonneSelectedDTO;
import com.mycompany.fitmanager.web.entity.Abonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbonneRepository extends JpaRepository<Abonne, Integer> {

    @Query("SELECT COUNT(a) FROM Abonne a")
    Integer totalAbonne(); // Nombre total d'abonnés

    @Query("SELECT new com.mycompany.fitmanager.web.dto.AbonneSansAbonnementDTO(a.id, a.nom, a.prenom, a.telephone, MAX(ab.dateFin)) " +
            "FROM Abonne a LEFT JOIN a.abonnements ab " +
            "WHERE ab.statutAbonnement = 'Inactif' OR ab.dateFin < CURRENT_DATE " +
            "GROUP BY a.id " +
            "HAVING COUNT(ab) = (SELECT COUNT(ab2) FROM Abonnement ab2 WHERE ab2.abonne = a AND ab2.statutAbonnement = 'Inactif')")
    List<AbonneSansAbonnementDTO> findAbonnesSansAbonnementActif(); // Les abonnés sans abonnements actifs

    @Query(" SELECT new com.mycompany.fitmanager.web.dto.AbonneSelectedDTO(a.id, a.nom, a.prenom, a.telephone) FROM Abonne a")
    List<AbonneSelectedDTO> findAbonneSelected(); // Abonné avec id, nom, prénom et téléphone
}