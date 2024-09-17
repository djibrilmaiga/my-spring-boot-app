package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.Abonne;
import com.mycompany.fitmanager.web.entity.Abonnement;
import com.mycompany.fitmanager.web.entity.Paiement;
import com.mycompany.fitmanager.web.entity.enums.ModePaiement;
import com.mycompany.fitmanager.web.entity.enums.Statut;
import com.mycompany.fitmanager.web.entity.enums.StatutPaiement;
import com.mycompany.fitmanager.web.entity.enums.TypePaiement;
import com.mycompany.fitmanager.web.repository.AbonneRepository;
import com.mycompany.fitmanager.web.repository.AbonnementRepository;
import com.mycompany.fitmanager.web.repository.PaiementRepository;
import com.mycompany.fitmanager.web.repository.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AbonneService {

    @Autowired
    private AbonneRepository abonneRepository;

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PaiementRepository paiementRepository;

    // Inscrire un nouvel abonné
    public Abonne inscrireAbonne(Abonne newAbonne, Integer serviceIdInscription, BigDecimal montantPaye, ModePaiement modePaiement) {
        // Vérifier que le service d'inscription existe
        com.mycompany.fitmanager.web.entity.Service service = serviceRepository
                .findById(serviceIdInscription)
                .orElseThrow(() -> new RuntimeException("Service d'inscription non trouvé"));

        // Vérifier que le montant payé est exactement égal au tarif du service
        if (montantPaye.compareTo(service.getTarif()) != 0) {
            throw new RuntimeException("Le montant payé pour l'inscription doit correspondre exactement au tarif du service.");
        }
        // Augmentation du PaiementTotal de l'abonné
        newAbonne.setPaiementTotal(newAbonne.getPaiementTotal().add(montantPaye));

        // Enregistrer le nouvel abonné
        Abonne abonne = abonneRepository.save(newAbonne);

        // Créer et enregistrer un abonnement pour le service d'inscription
        Abonnement abonnement = new Abonnement(LocalDate.now(), service, abonne, null);
        abonnementRepository.save(abonnement);

        // Créer et enregistrer le paiement
        Paiement paiement = new Paiement();
        paiement.setAbonne(abonne);
        paiement.setService(service);
        paiement.setMontantTotal(service.getTarif());
        paiement.setMontantPaye(montantPaye);
        paiement.setMontantRestant(BigDecimal.ZERO); // Pas de montant restant pour une inscription.
        paiement.setDatePaiement(LocalDate.now());
        paiement.setTypePaiement(TypePaiement.Inscription); // Le type de paiement est "Inscription".
        paiement.setModePaiement(modePaiement);
        paiement.setStatutPaiement(StatutPaiement.Complet); // Le paiement est complet.
        paiement.setCommentaire("Paiement complet pour inscription");

        // Enregistrer le paiement
        paiementRepository.save(paiement);

        // Retourner l'abonné inscrit
        return abonne;
    }
    // Obtenir un abonné
    public Abonne obtenirAbonne(Integer id){
        return abonneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membre non trouvé avec l'id : " + id));
    }
    // Obtenir tous les abonnés
    public List<Abonne> obtenirTousLesAbonnes(){
        return abonneRepository.findAll();
    }
    // Obtenir tous les Abonnés actifs
    public List<Abonne> obtenirTousLesAbonnesActifs(){
        return abonneRepository.findByStatutAbonnement(Statut.Actif);
    }
    // Modifier un abonné
    public Abonne modifierAbonne(Abonne abonne){
        if (abonneRepository.existsById(abonne.getId())) {
            return abonneRepository.save(abonne);
        } else {
            throw new EntityNotFoundException("Membre non trouvé avec l'ID: " + abonne.getId());
        }    }
    // Supprimer un abonné
    public void supprimerAbonne(Integer id){
        abonneRepository.deleteById(id);
    }
    // Rechercher un membre par son téléphone
    public Abonne rechercherParTelephone(String telephone){
        return abonneRepository.findByTelephone(telephone)
                .orElseThrow(() -> new RuntimeException("Membre non trouvé avec le téléphone : "+ telephone));
    }
    // Rechercher un abonné par son nom et prénom
    public List<Abonne> rechercherNomEtPrenon(String nom, String prenom){
        return abonneRepository.findByNomAndPrenom(nom, prenom);
    }

}
