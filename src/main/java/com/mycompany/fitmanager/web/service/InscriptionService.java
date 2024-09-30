package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.dto.InscriptionDTO;
import com.mycompany.fitmanager.web.entity.Inscription;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.InscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    // POST
    public Inscription createInscription(Inscription newInscription){
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