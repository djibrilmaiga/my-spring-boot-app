package com.mycompany.fitmanager.web.entity;

import jakarta.persistence.*;

@Entity
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipement_id")
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private Integer quantite;

    private String photo;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "type_equipement_id")
    private TypeEquipement typeEquipement;

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public TypeEquipement getCategorieEquipement() {
        return typeEquipement;
    }

    public void setCategorieEquipement(TypeEquipement typeEquipement) {
        this.typeEquipement = typeEquipement;
    }
}
