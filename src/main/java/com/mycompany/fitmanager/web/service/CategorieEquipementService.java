package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.Categorie;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieEquipementService {

    @Autowired
    private CategorieRepository categorieRepository;

    // POST
    public Categorie createCategorie(Categorie newCategorie){
        return categorieRepository.save(newCategorie);
    }
    // Get ALL
    public List<Categorie> getAllCategories(){
        return categorieRepository.findAll();
    }
    
    // GET ID
    public Categorie getCategorieById(Integer categorieId){
        return categorieRepository.findById(categorieId)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie introuvable à l'Id :" + categorieId));
    }
    
    // PUT
    public Categorie updateCategorie(Integer categorieId, Categorie newCategorie){
        Categorie categorie = categorieRepository.findById(categorieId)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie introuvable à l'Id :" + categorieId));

        categorie.setLibelle(newCategorie.getLibelle());
        return categorieRepository.save(categorie);
    }
    
    // DELETE
    public void deleteCategorie(Integer categorieId){
        categorieRepository.findById(categorieId)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie introuvable à l'Id :" + categorieId));
        categorieRepository.deleteById(categorieId);
    }
}
