package com.mycompany.fitmanager.web.dto;

import com.mycompany.fitmanager.web.entity.enums.EtatEXemplaire;

import java.time.LocalDate;

public class ExemplaireDTO {

    private Integer exemplaireId;
    private String numSerie;
    private EtatEXemplaire etat;
    private LocalDate dateDernierMaintenance;
    private String nomEquipement;
    private String nomCategorie;

    // Constructors

    public ExemplaireDTO(Integer exemplaireId, String numSerie, EtatEXemplaire etat, LocalDate dateDernierMaintenance, String nomEquipement, String nomCategorie) {
        this.exemplaireId = exemplaireId;
        this.numSerie = numSerie;
        this.etat = etat;
        this.dateDernierMaintenance = dateDernierMaintenance;
        this.nomEquipement = nomEquipement;
        this.nomCategorie = nomCategorie;
    }

    // Getters et Setters

    public Integer getExemplaireId() {
        return exemplaireId;
    }

    public void setExemplaireId(Integer exemplaireId) {
        this.exemplaireId = exemplaireId;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public EtatEXemplaire getEtat() {
        return etat;
    }

    public void setEtat(EtatEXemplaire etat) {
        this.etat = etat;
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

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
}
