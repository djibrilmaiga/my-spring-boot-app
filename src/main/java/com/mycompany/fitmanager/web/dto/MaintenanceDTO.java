package com.mycompany.fitmanager.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MaintenanceDTO {

    private Integer maintenanceId;
    private LocalDate dateMaintenance;
    private BigDecimal coutMaintenance;
    private String description;
    private String nomTechnicen;
    private String prenomTechnicien;
    private String numSerieExemplaire;
    private String nomEquipement;

    // Constructors
    public MaintenanceDTO(Integer maintenanceId, LocalDate dateMaintenance, BigDecimal coutMaintenance, String description, String nomTechnicen, String prenomTechnicien, String numSerieExemplaire, String nomEquipement) {
        this.maintenanceId = maintenanceId;
        this.dateMaintenance = dateMaintenance;
        this.coutMaintenance = coutMaintenance;
        this.description = description;
        this.nomTechnicen = nomTechnicen;
        this.prenomTechnicien = prenomTechnicien;
        this.numSerieExemplaire = numSerieExemplaire;
        this.nomEquipement = nomEquipement;
    }

    // Getters et Setters
    public Integer getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Integer maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public LocalDate getDateMaintenance() {
        return dateMaintenance;
    }

    public void setDateMaintenance(LocalDate dateMaintenance) {
        this.dateMaintenance = dateMaintenance;
    }

    public BigDecimal getCoutMaintenance() {
        return coutMaintenance;
    }

    public void setCoutMaintenance(BigDecimal coutMaintenance) {
        this.coutMaintenance = coutMaintenance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomTechnicen() {
        return nomTechnicen;
    }

    public void setNomTechnicen(String nomTechnicen) {
        this.nomTechnicen = nomTechnicen;
    }

    public String getPrenomTechnicien() {
        return prenomTechnicien;
    }

    public void setPrenomTechnicien(String prenomTechnicien) {
        this.prenomTechnicien = prenomTechnicien;
    }

    public String getNumSerieExemplaire() {
        return numSerieExemplaire;
    }

    public void setNumSerieExemplaire(String numSerieExemplaire) {
        this.numSerieExemplaire = numSerieExemplaire;
    }

    public String getNomEquipement() {
        return nomEquipement;
    }

    public void setNomEquipement(String nomEquipement) {
        this.nomEquipement = nomEquipement;
    }
}