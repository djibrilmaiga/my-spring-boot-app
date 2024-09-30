package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.TypeAbonnement;
import com.mycompany.fitmanager.web.repository.TypeAbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceService {

    @Autowired
    private TypeAbonnementRepository typeAbonnementRepository;

    // Creer un service
    public TypeAbonnement creerService(TypeAbonnement typeAbonnement) {
        return typeAbonnementRepository.save(typeAbonnement);
    }
    // Obtenir un service
    public TypeAbonnement obtenirServiceParId(Integer id){
        return typeAbonnementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service non trouvé à l'id : " + id));
    }
    // Obtenir tous les services
    public List<TypeAbonnement> obtenirTousLesServices() {
        return typeAbonnementRepository.findAll();
    }
    // Mettre à jour un service
    public TypeAbonnement modifierService(TypeAbonnement typeAbonnement){
        return typeAbonnementRepository.save(typeAbonnement);
    }
    // Supprimer un service
    public void supprimerService(Integer id){
        typeAbonnementRepository.deleteById(id);
    }
}
