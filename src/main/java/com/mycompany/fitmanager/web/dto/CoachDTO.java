package com.mycompany.fitmanager.web.dto;

public class CoachDTO {
    private String nom;
    private String prenom;

    // Constructeurs
    public CoachDTO(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    // Getters et Setters
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
