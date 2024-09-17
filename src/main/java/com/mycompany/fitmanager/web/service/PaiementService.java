package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.Abonne;
import com.mycompany.fitmanager.web.entity.Abonnement;
import com.mycompany.fitmanager.web.entity.Paiement;
import com.mycompany.fitmanager.web.entity.enums.*;
import com.mycompany.fitmanager.web.repository.AbonneRepository;
import com.mycompany.fitmanager.web.repository.AbonnementRepository;
import com.mycompany.fitmanager.web.repository.PaiementRepository;
import com.mycompany.fitmanager.web.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private AbonneRepository abonneRepository;

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public Paiement traiterPaiement(Integer abonneId, Integer serviceId, Paiement newPaiement, LocalDate dateDebutAbonnement) {
        // Récupérer le service correspondant
        com.mycompany.fitmanager.web.entity.Service service = serviceRepository
                .findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service non trouvé"));

        // Récupérer l'abonné correspondant
        Abonne abonne = abonneRepository.findById(abonneId)
                .orElseThrow(() -> new RuntimeException("Abonné non trouvé"));

        // Vérifier si le premier abonnement est bien une inscription
        Abonnement premierAbonnement = abonnementRepository.findFirstByAbonneOrderByDateDebutAsc(abonne).orElseThrow(() -> new RuntimeException("Aucun abonnement trouvé !"));
        if (premierAbonnement == null || premierAbonnement.getService().getTypeService() != TypeService.Inscription) {
            throw new RuntimeException("Le premier abonnement de l'abonné doit être lié à un paiement de type Inscription.");
        }

        // Vérifier s'il existe un abonnement actif pour ce service
        Abonnement abonnementActif = abonnementRepository.findFirstByAbonneAndServiceAndStatutOrderByDateFinDesc(abonne, service, Statut.Actif).orElseThrow(() -> new RuntimeException("Aucun abonnement actif !"));
        if (abonnementActif != null && abonnementActif.getDateFin().isAfter(LocalDate.now())) {
            throw new RuntimeException("Un abonnement actif existe déjà pour ce service. Aucun nouveau paiement de type Abonnement ne peut être effectué.");
        }

        // Créer un nouvel abonnement
        Abonnement nouvelAbonnement = new Abonnement();
        nouvelAbonnement.setService(service);
        nouvelAbonnement.setAbonne(abonne);

        // Si le paiement concerne un Abonnement
        if (newPaiement.getTypePaiement() == TypePaiement.Abonnement) {
            // Définir la date de début de l'abonnement
            nouvelAbonnement.setDateDebut(dateDebutAbonnement);
            nouvelAbonnement.calculerDateFin();
            // Modifier le statut de l'abonnement à Actif
            abonne.setStatut(Statut.Actif);
        }
        // Si le paiement concerne une Inscription (renouvellement)
        else if (newPaiement.getTypePaiement() == TypePaiement.Inscription) {
            // La date de début d'une inscription commence dès le paiement
            nouvelAbonnement.setDateDebut(LocalDate.now());
            nouvelAbonnement.calculerDateFin();
            // Vérifier si l'abonnement actuel est inactif avant de renouveler l'inscription
            if (abonnementActif != null && abonnementActif.getStatut() == Statut.Actif) {
                abonnementActif.setStatut(Statut.Inactif);
                abonnementRepository.save(abonnementActif);
            }
        }

        // Calcul du montant total basé sur le tarif du service
        BigDecimal montantTotal = service.getTarif();

        // Créer le paiement
        Paiement paiement = new Paiement();
        paiement.setAbonne(nouvelAbonnement.getAbonne());
        paiement.setService(service);
        paiement.setMontantTotal(montantTotal);
        paiement.setMontantPaye(newPaiement.getMontantPaye());
        paiement.setMontantRestant(montantTotal.subtract(newPaiement.getMontantPaye()));
        paiement.setDatePaiement(LocalDate.now());
        paiement.setTypePaiement(newPaiement.getTypePaiement());
        paiement.setModePaiement(newPaiement.getModePaiement());

        // Vérifier que le paiement pour une inscription est complet
        if (newPaiement.getTypePaiement() == TypePaiement.Inscription && paiement.getMontantRestant().compareTo(BigDecimal.ZERO) > 0) {
            throw new RuntimeException("Le paiement pour une inscription doit être complet.");
        }

        // Définir le statut du paiement
        if (paiement.getMontantRestant().compareTo(BigDecimal.ZERO) == 0) {
            paiement.setStatutPaiement(StatutPaiement.Complet);
            paiement.setCommentaire("Paiement final");
        } else {
            paiement.setStatutPaiement(StatutPaiement.Partiel);
            paiement.setCommentaire("Paiement partiel effectué");
        }

        // Incrémenter le paiementTotal de l'abonné
        abonne.setPaiementTotal(abonne.getPaiementTotal().add(newPaiement.getMontantPaye()));

        // Enregistrer l'abonné, le paiement et l'abonnement
        abonneRepository.save(abonne);
        abonnementRepository.save(nouvelAbonnement);
        return paiementRepository.save(paiement);
    }


    public Paiement modifierPaiementPartiel(Integer paiementId, BigDecimal montantPaye, ModePaiement modePaiement) {
        // Rechercher un paiement existant
        Paiement paiement = paiementRepository.findById(paiementId)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé"));

        // Ajouter le montant payé
        paiement.setMontantPaye(paiement.getMontantPaye().add(montantPaye));
        paiement.setMontantRestant(paiement.getMontantTotal().subtract(paiement.getMontantPaye()));
        paiement.setDatePaiement(LocalDate.now());
        paiement.setModePaiement(modePaiement);

        // Mettre à jour le statut
        if (paiement.getMontantRestant().compareTo(BigDecimal.ZERO) == 0) {
            paiement.setStatutPaiement(StatutPaiement.Complet);
            paiement.setCommentaire("Paiement final");
        } else {
            paiement.setStatutPaiement(StatutPaiement.Partiel);
            paiement.setCommentaire("Paiement partiel effectué");
        }

        // Incrementer le paiementTotal de l'abonné
        Abonne abonne = paiement.getAbonne();
        abonne.setPaiementTotal(abonne.getPaiementTotal().add(montantPaye));
        abonneRepository.save(abonne);

        // Enregistrer le paiement mis à jour
        return paiementRepository.save(paiement);
    }

    // Obtenir tous les paiements
    public List<Paiement> obtenirTousLesPaiements(){
        return paiementRepository.findAll();
    }
}
