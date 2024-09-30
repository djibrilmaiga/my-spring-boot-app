package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.dto.TypeAbonnementDTO;
import com.mycompany.fitmanager.web.entity.TypeAbonnement;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.TypeAbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeAbonnementService {

    @Autowired
    private TypeAbonnementRepository typeAbonnementRepository;

    // POST
    public TypeAbonnement createTypeAbonnement(TypeAbonnement newTypeAbonnement) {
        TypeAbonnement typeAbonnement = typeAbonnementRepository.save(newTypeAbonnement);
        return typeAbonnement;
    }
    // GET ID
    public TypeAbonnementDTO getTypeAbonnementById(Integer typeAbonnementId){
        TypeAbonnementDTO typeAbonnement = typeAbonnementRepository.findTypeAbonnementById(typeAbonnementId);
        return typeAbonnement;
    }
    // GET ALL
    public List<TypeAbonnementDTO> getAllTypeAbonnement() {
        List<TypeAbonnementDTO> typeAbonnements = typeAbonnementRepository.findAllType();
        return typeAbonnements;
    }

    // PUT
    public TypeAbonnement updateTypeAbonnement(Integer typeId, TypeAbonnement newTypeAbonnement){
        TypeAbonnement LastTypeAbonnement = typeAbonnementRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("Service Abonnement non trouvé à l'id : " + typeId));

        LastTypeAbonnement.setLibelle(newTypeAbonnement.getLibelle());
        LastTypeAbonnement.setDureeJour(newTypeAbonnement.getDureeJour());
        LastTypeAbonnement.setTarif(newTypeAbonnement.getTarif());
        LastTypeAbonnement.setDescription(newTypeAbonnement.getDescription());

        return typeAbonnementRepository.save(LastTypeAbonnement);
    }

    // DELETE
    public void deleteTypeAbonnement(Integer typeId){
        TypeAbonnement typeAbonnement = typeAbonnementRepository.findById(typeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Service Abonnement non trouvé à l'id : " + typeId));

        typeAbonnementRepository.deleteById(typeId);
    }
}