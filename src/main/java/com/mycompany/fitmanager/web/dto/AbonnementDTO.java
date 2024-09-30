package com.mycompany.fitmanager.web.dto;

import com.mycompany.fitmanager.web.entity.enums.Statut;

import java.time.LocalDate;

public class AbonnementDTO {
    private Integer abonnementId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Statut statutAbonnement;
    private String nomAbonne;
    private String prenomAbonne;

    // Constructeurs
    public AbonnementDTO(Integer abonnementId, LocalDate dateDebut, LocalDate dateFin, Statut statutAbonnement, String nomAbonne, String prenomAbonne) {
        this.abonnementId = abonnementId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statutAbonnement = statutAbonnement;
        this.nomAbonne = nomAbonne;
        this.prenomAbonne = prenomAbonne;
    }

    // Getters et Setters
    public Integer getAbonnementId() {
        return abonnementId;
    }

    public void setAbonnementId(Integer abonnementId) {
        this.abonnementId = abonnementId;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getNomAbonne() {
        return nomAbonne;
    }

    public void setNomAbonne(String nomAbonne) {
        this.nomAbonne = nomAbonne;
    }

    public String getPrenomAbonne() {
        return prenomAbonne;
    }

    public void setPrenomAbonne(String prenomAbonne) {
        this.prenomAbonne = prenomAbonne;
    }

    public Statut getStatutAbonnement() {
        return statutAbonnement;
    }

    public void setStatutAbonnement(Statut statutAbonnement) {
        this.statutAbonnement = statutAbonnement;
    }
}
