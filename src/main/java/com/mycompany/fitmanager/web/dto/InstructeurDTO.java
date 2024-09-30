package com.mycompany.fitmanager.web.dto;

public class InstructeurDTO {

    private Integer instructeurId;
    private String nom;
    private String prenom;
    private String telephone;
    private String specialite;

    // Constructors
    public InstructeurDTO(Integer instructeurId, String nom, String prenom, String telephone, String specialite) {
        this.instructeurId = instructeurId;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.specialite = specialite;
    }

    // Getters et Setters

    public Integer getInstructeurId() {
        return instructeurId;
    }

    public void setInstructeurId(Integer instructeurId) {
        this.instructeurId = instructeurId;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
