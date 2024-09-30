package com.mycompany.fitmanager.web.dto;

public class EquipementDTO {

    private Integer id;
    private String nomEquipement;
    private Integer quantite;
    private String nomCategorie;

    // Constructor
    public EquipementDTO(Integer id, String nomEquipement, Integer quantite, String categorie) {
        this.id = id;
        this.nomEquipement = nomEquipement;
        this.quantite = quantite;
        this.nomCategorie = categorie;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomEquipement() {
        return nomEquipement;
    }

    public void setNomEquipement(String nomEquipement) {
        this.nomEquipement = nomEquipement;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
}
