package com.mycompany.fitmanager.web.entity;

import com.mycompany.fitmanager.web.entity.enums.TypeService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeService typeService;

    @NotNull
    @Column(nullable = false)
    private String nom;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Column(nullable = false)
    private BigDecimal tarif;

    @NotNull
    @Column(nullable = false)
    private Integer dureeJour;

    // Constructeurs
    public Service() {
    }

    public Service(String nom, BigDecimal tarif, Integer dureeJour) {
        this.nom = nom;
        this.tarif = tarif;
        this.dureeJour = dureeJour;
    }
    //Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }

    public Integer getDureeJour() {
        return dureeJour;
    }

    public void setDureeJour(Integer dureeJour) {
        this.dureeJour = dureeJour;
    }
}
