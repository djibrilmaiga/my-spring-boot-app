package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.dto.AbonnementDTO;
import com.mycompany.fitmanager.web.entity.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    // Trouver tous les abonnements liés à un abonné
    List<Abonnement> findByAbonneId(Integer abonneId);

    @Query("SELECT new com.mycompany.fitmanager.web.dto.AbonnementDTO(a.id, a.dateDebut, a.dateFin, a.statutAbonnement, ab.nom, ab.prenom) " +
            "FROM Abonnement a JOIN a.abonne ab")
    List<AbonnementDTO> findAllAbonnementsWithAbonneInfo();
}