package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.dto.InscriptionDTO;
import com.mycompany.fitmanager.web.entity.Inscription;
import com.mycompany.fitmanager.web.entity.Utilisateur;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.InscriptionRepository;
import com.mycompany.fitmanager.web.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // POST
    public Inscription createInscription(Integer userId, Inscription newInscription){
        // Récuperer l'utilisateur qui crée l'inscription
        Utilisateur user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé à l'ID: "+ userId));
        // Affectation de l'utilisateur à l'entité Inscription en cours...
        newInscription.setUtilisateur(user);
        // Enregistrement de l'inscription
        Inscription inscription = inscriptionRepository.save(newInscription);
        return inscription;
    }

    // GET ID
    public InscriptionDTO getInscriptionById(Integer inscriptionId){
        InscriptionDTO inscription = inscriptionRepository.findInscriptionById(inscriptionId);
        return inscription;
    }

    // GET ALL
    public List<InscriptionDTO> getAllInscription(){
        List<InscriptionDTO> inscriptions = inscriptionRepository.findAllInscription();
        return inscriptions;
    }

    // PUT
    public Inscription updateInscription(Integer inscriptionId, Inscription newInscription){
        Inscription lastInscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Inscription non trouvé à l'id : " + inscriptionId));

        lastInscription.setLibelle(newInscription.getLibelle());
        lastInscription.setTarif(newInscription.getTarif());
        lastInscription.setDescription(newInscription.getDescription());

        return inscriptionRepository.save(lastInscription);
    }

    // DELETE
    public void deleteInscription(Integer inscriptionId){
        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Inscription non trouvé à l'id : " + inscriptionId));
        inscriptionRepository.deleteById(inscriptionId);
    }
}