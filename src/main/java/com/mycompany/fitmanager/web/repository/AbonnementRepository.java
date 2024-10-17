package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.dto.AbonnementExpirationDTO;
import com.mycompany.fitmanager.web.entity.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {

    List<Abonnement> findByAbonneId(Integer abonneId); // Abonnements d'un Abonné

    @Query("SELECT new com.mycompany.fitmanager.web.dto.AbonnementExpirationDTO(a.id, a.dateFin, " +
            "DATEDIFF(a.dateFin, :currentDate), ab.nom, ab.prenom) " +
            "FROM Abonnement a " +
            "JOIN a.abonne ab " +
            "WHERE a.statutAbonnement = 'ACTIF' " +
            "AND DATEDIFF(a.dateFin, :currentDate) <= :joursRestants")
    List<AbonnementExpirationDTO> findAbonnementsExpirant(@Param("currentDate") LocalDate currentDate,@Param("joursRestants") Integer joursRestants);
}