package com.mycompany.fitmanager.web.dto;

import java.time.LocalDate;

public class AbonneSansAbonnementDTO {
    private String nom;
    private String prenom;
    private String telephone;
    private LocalDate derniereDateFinAbonnement;

    // Constructeurs
    public AbonneSansAbonnementDTO(String nom, String prenom, String telephone, LocalDate derniereDateFinAbonnement) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.derniereDateFinAbonnement = derniereDateFinAbonnement;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getDerniereDateFinAbonnement() {
        return derniereDateFinAbonnement;
    }

    public void setDerniereDateFinAbonnement(LocalDate derniereDateFinAbonnement) {
        this.derniereDateFinAbonnement = derniereDateFinAbonnement;
    }
}

