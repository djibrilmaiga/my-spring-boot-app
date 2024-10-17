package com.mycompany.fitmanager.web.dto;

import java.time.LocalDate;

public class AbonnementExpirationDTO {
    private Integer id;
    private LocalDate dateFin;
    private Integer joursRestants;
    private String nom;
    private String prenom;

    // Constructors
    public AbonnementExpirationDTO(Integer id, LocalDate dateFin, Integer joursRestants, String nom, String prenom) {
        this.id = id;
        this.dateFin = dateFin;
        this.joursRestants = joursRestants;
        this.nom = nom;
        this.prenom = prenom;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getJoursRestants() {
        return joursRestants;
    }

    public void setJoursRestants(Integer joursRestants) {
        this.joursRestants = joursRestants;
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
}