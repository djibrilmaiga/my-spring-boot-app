package com.mycompany.fitmanager.web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TypeAbonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_abonnement_id")
    private Integer id;


    @NotNull
    @Column(nullable = false)
    private String libelle;

    @NotNull
    @Column(nullable = false)
    private Integer dureeJour;

    @NotNull
    @Column(nullable = false)
    private BigDecimal tarif;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull String getLibelle() {
        return libelle;
    }

    public void setLibelle(@NotNull String libelle) {
        this.libelle = libelle;
    }

    public @NotNull Integer getDureeJour() {
        return dureeJour;
    }

    public void setDureeJour(@NotNull Integer dureeJour) {
        this.dureeJour = dureeJour;
    }

    public @NotNull BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(@NotNull BigDecimal tarif) {
        this.tarif = tarif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
