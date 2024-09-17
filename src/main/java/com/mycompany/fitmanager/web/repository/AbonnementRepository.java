package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Abonne;
import com.mycompany.fitmanager.web.entity.Abonnement;
import com.mycompany.fitmanager.web.entity.Service;
import com.mycompany.fitmanager.web.entity.enums.Statut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    // Trouver tous les abonnements liés à un abonné
    List<Abonnement> findByAbonneId(Integer abonneId);

    // Trouver tous les abonnements par statut
    List<Abonnement> findByStatut(Statut statut);

    // Trouver le premier abonnement d'un abonné, trié par date de début
    Optional<Abonnement> findFirstByAbonneOrderByDateDebutAsc(Abonne abonne);

    // Trouver le premier abonnement d'un abonné pour un service spécifique avec un statut donné, trié par date de fin
    Optional<Abonnement> findFirstByAbonneAndServiceAndStatutOrderByDateFinDesc(Abonne abonne, Service service, Statut statut);
}
