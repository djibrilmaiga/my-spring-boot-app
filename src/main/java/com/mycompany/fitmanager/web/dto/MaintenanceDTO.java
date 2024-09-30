package com.mycompany.fitmanager.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MaintenanceDTO {

    private Integer maintenanceId;
    private LocalDate dateMaintenance;
    private BigDecimal coutMaintenance;
    private String nomTechnicen;
    private String prenomTechnicien;
    private String numSerieExemplaire;
    private LocalDate dateDernierMaintenance;
    private String nomEquipement;

    // Constructors
    public MaintenanceDTO(Integer maintenanceId, LocalDate dateMaintenance, BigDecimal coutMaintenance, String nomTechnicen, String prenomTechnicien, String numSerieExemplaire, LocalDate dateDernierMaintenance, String nomEquipement) {
        this.maintenanceId = maintenanceId;
        this.dateMaintenance = dateMaintenance;
        this.coutMaintenance = coutMaintenance;
        this.nomTechnicen = nomTechnicen;
        this.prenomTechnicien = prenomTechnicien;
        this.numSerieExemplaire = numSerieExemplaire;
        this.dateDernierMaintenance = dateDernierMaintenance;
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

    public LocalDate getDateDernierMaintenance() {
        return dateDernierMaintenance;
    }

    public void setDateDernierMaintenance(LocalDate dateDernierMaintenance) {
        this.dateDernierMaintenance = dateDernierMaintenance;
    }

    public String getNomEquipement() {
        return nomEquipement;
    }

    public void setNomEquipement(String nomEquipement) {
        this.nomEquipement = nomEquipement;
    }
}
