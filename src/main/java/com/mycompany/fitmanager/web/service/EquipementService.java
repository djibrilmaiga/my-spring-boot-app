package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.dto.EquipementDTO;
import com.mycompany.fitmanager.web.entity.Categorie;
import com.mycompany.fitmanager.web.entity.Equipement;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.CategorieRepository;
import com.mycompany.fitmanager.web.repository.EquipementRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class EquipementService {

    @Autowired
    private EquipementRepository equipementRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    // POST
    public Equipement createEquipement(Integer categorieId, Equipement equipement){
        Categorie categorie = categorieRepository.findById(categorieId)
                        .orElseThrow(() -> new ResourceNotFoundException("Categorie Introuvable à l'ID : " + categorieId));
        equipement.setCategorie(categorie);
        equipement.setQuantite(0);
        return equipementRepository.save(equipement);
    }

    // GET ALL
    public List<EquipementDTO> getAllEquipement(){
        return equipementRepository.findEquipementWithCategorie();
    }

    // PUT
    public Equipement updateEquipement(Integer id, Equipement newEquipement){
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement introuvable à l'Id : " + id));
        equipement.setNom(newEquipement.getNom());
        return equipementRepository.save(equipement);
    }

    // DELETE
    public void deleteEquipement(Integer equipementId){
        equipementRepository.findById(equipementId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement introuvable à l'Id : " + equipementId));
        equipementRepository.deleteById(equipementId);
    }
}
