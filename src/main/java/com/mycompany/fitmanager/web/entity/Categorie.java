package com.mycompany.fitmanager.web.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TypeEquipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_equipement_id")
    private Integer id;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    // Contient la liste des équipements selon la catégorie d'équipement
    @OneToMany(mappedBy = "typeEquipement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipement> equipements = new ArrayList<>();

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
