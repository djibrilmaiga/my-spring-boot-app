package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.Abonnement;
import com.mycompany.fitmanager.web.repository.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AbonnementService {

    @Autowired
    private AbonnementRepository abonnementRepository;

    // Creer un abonnement
    public Abonnement creerAbonnement(Abonnement abonnement){
        abonnement.calculerDateFin();
        return abonnementRepository.save(abonnement);
    }
    // Obtenir un abonnement
    public Abonnement obtenirAbonnementParId(Integer id){
        return abonnementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("L'abonnement non trouvé à l'id : " + id));
    }
    // Obtenir tous les abonnements
    public List<Abonnement> obtenirTousLesAbonnements(){
        return abonnementRepository.findAll();
    }
    // Obtenir tous les abonnements par abonné
    public List<Abonnement> obtenirAbonnementsParAbonne(Integer id){
        return abonnementRepository.findByAbonneId(id);
    }
    // Mettre à jour un abonnement
    public Abonnement modifierAbonnement(Abonnement abonnement){
        abonnement.calculerDateFin();
        return abonnementRepository.save(abonnement);
    }
    // Supprimer un abonnement
    public void supprimerAbonnement(Integer id){
        abonnementRepository.deleteById(id);
    }
}
