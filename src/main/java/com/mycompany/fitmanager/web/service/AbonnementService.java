package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.dto.AbonnementDTO;
import com.mycompany.fitmanager.web.dto.AbonnementExpirationDTO;
import com.mycompany.fitmanager.web.dto.AbonnementSuscribeDTO;
import com.mycompany.fitmanager.web.entity.Abonne;
import com.mycompany.fitmanager.web.entity.Abonnement;
import com.mycompany.fitmanager.web.entity.TypeAbonnement;
import com.mycompany.fitmanager.web.entity.enums.Statut;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.AbonneRepository;
import com.mycompany.fitmanager.web.repository.AbonnementRepository;
import com.mycompany.fitmanager.web.repository.TypeAbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AbonnementService {

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private AbonneRepository abonneRepository;

    @Autowired
    private TypeAbonnementRepository typeAbonnementRepository;

    // POST
    public String createAbonnement(Integer abonneId, AbonnementSuscribeDTO newAbonnement) {

        // Récupérer l'abonné à partir de son ID
        Abonne abonne = abonneRepository.findById(abonneId)
                .orElseThrow(() -> new ResourceNotFoundException("L'abonné avec l'ID " + abonneId + " n'existe pas."));

        // Récupérer tous les abonnements de l'abonné
        List<Abonnement> abonnements = abonnementRepository.findByAbonneId(abonneId);

        // Vérifier si l'abonné a un abonnement actif
        for (Abonnement abonnement : abonnements) {
            if (abonnement.getDateFin().isAfter(LocalDate.now()) && abonnement.getStatutAbonnement() == Statut.Actif) {
                // Si un abonnement est toujours actif, renvoyer un message d'erreur
                return "L'abonné a encore un abonnement en cours.";
            }
        }

        // Si aucun abonnement actif, on crée un nouvel abonnement
        Abonnement abonnement = new Abonnement();

        // Conversion du DTO en Abonnement
        abonnement.setDateDebut(newAbonnement.getDateDebut());
        abonnement.setDateFin(newAbonnement.getDateFin());
        abonnement.setStatutAbonnement(Statut.valueOf(newAbonnement.getStatutAbonnement()));

        // Récupérer l'ID du type d'abonnement
        TypeAbonnement typeAbonnement = typeAbonnementRepository.findById(newAbonnement.getTypeId())
                .orElseThrow(() -> new RuntimeException("Type d'abonnement introuvable"));

        // Affecter l'objet Type Abonnement à l'Abonnement
        abonnement.setType(typeAbonnement);

        // Affecter l'objet Abonné à l'abonnement
        abonnement.setAbonne(abonne);

        // Activer le statut du nouvel Abonnement
        abonnement.setStatutAbonnement(Statut.Actif);

        // Enregistrer l'Abonnement dans la Base de données
        abonnementRepository.save(abonnement);

        // Confirmer l'enregistrement de l'abonné par du texte et passer à l'instruction suivante
        return "Nouvel abonnement créé avec succès pour l'abonné.";
    }

    // GET ALL
    public List<AbonnementDTO> getAllAbonnementWithAbonneInf(){
        List<Abonnement> abonnements = abonnementRepository.findAll();

        for (Abonnement abonnement : abonnements) {
            if (abonnement.getDateFin().isBefore(LocalDate.now()) && abonnement.getStatutAbonnement() == Statut.Actif) {
                abonnement.setStatutAbonnement(Statut.Inactif);
                abonnementRepository.save(abonnement); // Sauvegarder la mise à jour
            }
        }

        // Convertir les abonnements en DTO pour la réponse
        return abonnements.stream()
                .map(abonnement -> new AbonnementDTO(
                        abonnement.getId(),
                        abonnement.getDateDebut(),
                        abonnement.getDateFin(),
                        abonnement.getStatutAbonnement(),
                        abonnement.getAbonne().getNom(),
                        abonnement.getAbonne().getPrenom()
                ))
                .collect(Collectors.toList());
    }

    // GET ALL Abonnements Actif proche d'expiration
    public List<AbonnementExpirationDTO> getAbonnementsProchesExpiration(Integer joursRestants) {
        LocalDate currentDate = LocalDate.now();
        return abonnementRepository.findAbonnementsExpirant(currentDate, joursRestants);
    }

}