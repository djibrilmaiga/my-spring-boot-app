package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.dto.ExemplaireDTO;
import com.mycompany.fitmanager.web.entity.Equipement;
import com.mycompany.fitmanager.web.entity.Exemplaire;
import com.mycompany.fitmanager.web.entity.enums.EtatEXemplaire;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.EquipementRepository;
import com.mycompany.fitmanager.web.repository.ExemplaireRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class ExemplaireService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private EquipementRepository equipementRepository;

    // POST
    public Exemplaire createExemplaire(Integer equipementId, Exemplaire exemplaire){
        Equipement equipement = equipementRepository.findById(equipementId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement introuvable à l'Id : " + equipementId));

        equipement.setQuantite(equipement.getQuantite() + 1);
        exemplaire.setEquipement(equipement);
        exemplaire.setEtat(EtatEXemplaire.Fonctionnel);
        exemplaire.setDateDernierMaintenance(null);
        equipementRepository.save(equipement);
        return exemplaireRepository.save(exemplaire);
    }

    // GET ALL
    public List<ExemplaireDTO> getAllExemplaire(){
        return exemplaireRepository.findAllExemplairewithEquiAndCat();
    }

    // GET
    public Exemplaire getExemplaireById(Integer exemplaireId){
        return exemplaireRepository.findById(exemplaireId)
                .orElseThrow(() -> new ResourceNotFoundException("Exemplaire introuvable à l'Id :" + exemplaireId));
    }

    // PUT
    public Exemplaire updateExemplaire(Integer exemplaireId, Exemplaire newExemplaire){
        Exemplaire exemplaire = exemplaireRepository.findById(exemplaireId)
                .orElseThrow(() -> new ResourceNotFoundException("Exemplaire introuvable à l'Id : " + exemplaireId));
        exemplaire.setNumSerie(newExemplaire.getNumSerie());
        exemplaire.setEtat(newExemplaire.getEtat());

        return exemplaireRepository.save(exemplaire);
    }
    // DELETE
    public void deleteExemplaire(Integer exemplaireId){
       Exemplaire exemplaire =  exemplaireRepository.findById(exemplaireId)
                .orElseThrow(() -> new ResourceNotFoundException("Exemplaire introuvable à l'Id : " + exemplaireId));

       Equipement equipement = exemplaire.getEquipement();
        equipement.setQuantite(equipement.getQuantite() - 1);
        equipementRepository.save(equipement);

        exemplaireRepository.deleteById(exemplaireId);

    }
}
