package com.mycompany.fitmanager.web.dto;

import com.mycompany.fitmanager.web.entity.enums.StatutPresence;

public class AbonneDTO {

    private Integer seanceId;
    private Integer abonneId;
    private String nom;
    private String prenom;
    private StatutPresence statut;

    // Constructors
    public AbonneDTO(Integer seanceId, Integer abonneId, String nom, String prenom, StatutPresence statut) {
        this.seanceId = seanceId;
        this.abonneId = abonneId;
        this.nom = nom;
        this.prenom = prenom;
        this.statut = statut;
    }

    // Getters et Setters
    public Integer getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(Integer seanceId) {
        this.seanceId = seanceId;
    }

    public Integer getAbonneId() {
        return abonneId;
    }

    public void setAbonneId(Integer abonneId) {
        this.abonneId = abonneId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public StatutPresence getStatut() {
        return statut;
    }

    public void setStatut(StatutPresence statut) {
        this.statut = statut;
    }
}
