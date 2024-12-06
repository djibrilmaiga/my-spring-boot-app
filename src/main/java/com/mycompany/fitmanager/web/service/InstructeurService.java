package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.Instructeur;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.InstructeurRepository;
import org.springframework.stereotype.Service;

@Service
public class InstructeurService extends AgentService<Instructeur> {

    private final InstructeurRepository instructeurRepository;

    public InstructeurService(InstructeurRepository instructeurRepository) {
        this.instructeurRepository = instructeurRepository;
    }

    // POST
    @Override
    public Instructeur save(Instructeur instructeur) {
        return instructeurRepository.save(instructeur);
    }


    // PUT
    public Instructeur updateInstructeur(Integer id, Instructeur newInstructeur) {
        Instructeur existingInstructeur = findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Instructeur introuvable à l'ID : " + id)
        );

        existingInstructeur.setNom(newInstructeur.getNom());
        existingInstructeur.setPrenom(newInstructeur.getPrenom());
        existingInstructeur.setTelephone(newInstructeur.getTelephone());
        existingInstructeur.setSpecialite(newInstructeur.getSpecialite());

        return save(existingInstructeur);
    }

}