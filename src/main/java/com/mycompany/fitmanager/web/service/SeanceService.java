package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.dto.CoachDTO;
import com.mycompany.fitmanager.web.entity.Abonne;
import com.mycompany.fitmanager.web.entity.Instructeur;
import com.mycompany.fitmanager.web.entity.Participation;
import com.mycompany.fitmanager.web.entity.Seance;
import com.mycompany.fitmanager.web.entity.enums.TypeSeance;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.AbonneRepository;
import com.mycompany.fitmanager.web.repository.InstructeurRepository;
import com.mycompany.fitmanager.web.repository.ParticipationRepository;
import com.mycompany.fitmanager.web.repository.SeanceRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service @Transactional @AllArgsConstructor
public class SeanceService {

    private  SeanceRepository seanceRepository;

    private  InstructeurRepository instructeurRepository;

    private ParticipationRepository participationRepository;

    private AbonneRepository abonneRepository;

    // POST
    public Seance createSeanceSelectedCoach(Integer instructeurId, @Valid Seance newSeance) {
        Instructeur instructeur = instructeurRepository.findById(instructeurId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructeur non trouvé à l'Id : " + instructeurId));

        // Validation du nombre de participants
        if (newSeance.getNbreParticipants() < 1) {
            throw new IllegalArgumentException("Le nombre de participants doit être supérieur à 0.");
        }

        // Déterminer le type de séance
        if (newSeance.getNbreParticipants() == 1) {
            newSeance.setTypeSeance(TypeSeance.Individuelle);
        } else {
            newSeance.setTypeSeance(TypeSeance.Collective);
        }

        newSeance.setInstructeur(instructeur);
        return seanceRepository.save(newSeance);
    }

    // GET ALL
    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }

    // GET ID
    public Seance getSeanceById(Integer id) {
        return seanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seance introuvable à l'Id : " + id));
    }

    // GET Instructeur qui anime une Seance par Id
    public CoachDTO getInstructeurBySeanceId(Integer seanceId) {
        return seanceRepository.findInstructeurBySeanceId(seanceId);
    }

    // PUT
    public Seance inscrireCours(Integer seanceId, Seance newSeance) {
        // Récupérer la séance existante
        Seance seance = seanceRepository.findById(seanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Séance introuvable à l'Id : " + seanceId));

        // Supprimer les participations existantes
        participationRepository.deleteAll(seance.getParticipations());
        seance.getParticipations().clear(); // Assurez-vous que la liste de participations est vide

        // Ajouter les nouvelles participations
        for (Participation participation : newSeance.getParticipations()) {
            // Récupérer l'id de l'abonné à partir de la participation
            Integer abonneId = participation.getId().getAbonneId();

            // Vérifier si l'abonné existe dans la base de données
            Abonne abonne = abonneRepository.findById(abonneId)
                    .orElseThrow(() -> new ResourceNotFoundException("Abonné introuvable à l'Id : " + abonneId));

            // Initialiser l'abonné dans la participation
            participation.setAbonne(abonne);

            // Lier la participation à la séance
            participation.setSeance(seance);

            // Ajouter la participation à la séance
            seance.getParticipations().add(participation);
        }

        // Sauvegarder la séance (les participations seront sauvegardées en cascade)
        return seanceRepository.save(seance);
    }

    public Seance updateSeance(Integer id,  Seance newSeance) {
        // Récupérer la séance existante
        Seance seance = seanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Séance introuvable à l'Id : " + id));

        // Vider les participations existantes (orphanRemoval = true dans l'entité Seance assure la suppression)
        seance.getParticipations().clear();

        // Parcourir les nouvelles participations à ajouter
        for (Participation newParticipation : newSeance.getParticipations()) {
            // Associer correctement la séance et l'abonné pour chaque participation
            newParticipation.setSeance(seance);

            // Ajouter chaque participation à la liste des participations de la séance
            seance.getParticipations().add(newParticipation);
        }

        // Mettre à jour les autres champs de la séance si nécessaire
        seance.setStatut(newSeance.getStatut());

        // Sauvegarder la séance avec les nouvelles participations
        return seanceRepository.save(seance);
    }


    // DELETE
    public void deleteSeance(Integer id) {
        // Vérifier si la séance existe
        seanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seance introuvable à l'Id : " + id));
        // Supprimer la seance par son Id
        seanceRepository.deleteById(id);
    }
}