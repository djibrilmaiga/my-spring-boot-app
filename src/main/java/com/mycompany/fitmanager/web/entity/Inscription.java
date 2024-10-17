package com.mycompany.fitmanager.web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inscription_id")
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String libelle;

    @NotNull
    @Column(nullable = false)
    private BigDecimal tarif;

    private String description;

    // Getters et Setters
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

    public @NotNull BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(@NotNull BigDecimal tarif) {
        this.tarif = tarif;
    }

    public @NotNull String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }
}