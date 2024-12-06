package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.dto.MaintenanceDTO;
import com.mycompany.fitmanager.web.entity.Exemplaire;
import com.mycompany.fitmanager.web.entity.Maintenance;
import com.mycompany.fitmanager.web.entity.Technicien;
import com.mycompany.fitmanager.web.entity.enums.EtatEXemplaire;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.ExemplaireRepository;
import com.mycompany.fitmanager.web.repository.MaintenanceRepository;
import com.mycompany.fitmanager.web.repository.TechnicienRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private TechnicienRepository technicienRepository;

    // POST
    public Maintenance createMaintenance(Integer exemplaireId, Integer technicienId, Maintenance newMaintenance){
        Exemplaire exemplaire = exemplaireRepository.findById(exemplaireId)
                .orElseThrow(() -> new ResourceNotFoundException("Exemplaire introuvable à l'Id : " + exemplaireId));
        Technicien technicien = technicienRepository.findById(technicienId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructeur introuvable à l'Id : " + exemplaireId));

        newMaintenance.setExemplaire(exemplaire);
        newMaintenance.setTechnicien(technicien);
        exemplaire.setDateDernierMaintenance(newMaintenance.getDateDebut());
        exemplaire.setEtat(EtatEXemplaire.Fonctionnel);
        exemplaireRepository.save(exemplaire);

        return maintenanceRepository.save(newMaintenance);
    }
    // GET ALL
    public List<MaintenanceDTO> getAllMaintenances() {
        return maintenanceRepository.findAllMaintenances().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // GET ID
    public MaintenanceDTO getMaintenanceById(Integer id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance introuvable à l'Id : " + id));
        return mapToDTO(maintenance);
    }

    // PUT
    public Maintenance updateMaintenance(Integer maintenanceId, Maintenance maintenance) {
        // Recherche la maintenance existante
        Maintenance savedMaintenance = maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance introuvable à l'Id : " + maintenanceId));

        // Vérifie si la dateDebut a été modifiée
        if (!savedMaintenance.getDateDebut().equals(maintenance.getDateDebut())) {
            Exemplaire exemplaire = savedMaintenance.getExemplaire();

            // Met à jour la dateDernierMaintenance de l'exemplaire avec la nouvelle date de début de maintenance
            exemplaire.setDateDernierMaintenance(maintenance.getDateDebut());
            exemplaireRepository.save(exemplaire);
        }

        // Mise à jour des autres champs de la maintenance
        savedMaintenance.setDateDebut(maintenance.getDateDebut());
        savedMaintenance.setCout(maintenance.getCout());
        savedMaintenance.setRapport(maintenance.getRapport());

        // Sauvegarde la maintenance mise à jour et l'exemplaire associé
        return maintenanceRepository.save(savedMaintenance);
    }

    // DELETE
    public void deleteMaintenance(Integer maintenanceId){
        maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance introuvable à l'Id : " + maintenanceId));
        maintenanceRepository.deleteById(maintenanceId);
    }

    // Mapping entity to DTO
    private MaintenanceDTO mapToDTO(Maintenance maintenance) {
        return new MaintenanceDTO(
                maintenance.getId(),
                maintenance.getDateDebut(),
                maintenance.getCout(),
                maintenance.getRapport(), // Ajouter le rapport dans le champ description
                maintenance.getTechnicien().getNom(),
                maintenance.getTechnicien().getPrenom(),
                maintenance.getExemplaire().getNumSerie(),
                maintenance.getExemplaire().getEquipement().getNom()
        );
    }
}
