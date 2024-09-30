package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.Instructeur;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.InstructeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructeurService {

    @Autowired
    private InstructeurRepository instructeurRepository;

    // POST
    public Instructeur createInstructeur(Instructeur instructeur){
        return instructeurRepository.save(instructeur);
    }
    // GET ALL
    public List<Instructeur> getAllInstructeurs(){
        return instructeurRepository.findAll();
    }
    // GET ID
    public Instructeur getInstructeurById(Integer instructeurId){
        return instructeurRepository.findById(instructeurId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructeur introuvable  à l'ID : " + instructeurId));
    }
    // PUT
    public Instructeur updateInstructeur(Integer instructeurId, Instructeur newInstructeur){
        Instructeur instructeur = instructeurRepository.findById(instructeurId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructeur introuvable à l'Id :" + instructeurId));

        instructeur.setNom(newInstructeur.getNom());
        instructeur.setPrenom(newInstructeur.getPrenom());
        instructeur.setTelephone(newInstructeur.getTelephone());
        instructeur.setSpecialite(newInstructeur.getSpecialite());

        return instructeurRepository.save(instructeur);
    }
    // DELETE
    public void deleteInstructeur(Integer instructeurId){
        instructeurRepository.deleteById(instructeurId);
    }
}
