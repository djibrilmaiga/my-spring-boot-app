package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.dto.AbonnementDTO;
import com.mycompany.fitmanager.web.entity.Abonnement;
import com.mycompany.fitmanager.web.entity.enums.Statut;
import com.mycompany.fitmanager.web.repository.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AbonnementService {

    @Autowired
    private AbonnementRepository abonnementRepository;

    // GET ALL
    public List<AbonnementDTO> getAllAbonnementWithAbonneInf(){
        List<Abonnement> abonnements = abonnementRepository.findAll();

        for (Abonnement abonnement : abonnements) {
            if (abonnement.getDateFin().isBefore(LocalDate.now()) && abonnement.getStatutAbonnement() == Statut.Actif) {
                abonnement.setStatutAbonnement(Statut.Inactif);
                abonnementRepository.save(abonnement); // Sauvegarder la mise à jour
            }
        }

        // Convertir les abonnements en DTO pour la réponse
        return abonnements.stream()
                .map(abonnement -> new AbonnementDTO(
                        abonnement.getId(),
                        abonnement.getDateDebut(),
                        abonnement.getDateFin(),
                        abonnement.getStatutAbonnement(),
                        abonnement.getAbonne().getNom(),
                        abonnement.getAbonne().getPrenom()
                ))
                .collect(Collectors.toList());
    }
}