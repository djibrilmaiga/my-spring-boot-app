package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_id")
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private BigDecimal cout;

    @Column(columnDefinition = "TEXT")
    private String rapport;

    @ManyToOne
    @JoinColumn(name = "exemplaire_id")
    @JsonBackReference(value = "exemplaire-maintenances")
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "technicien_id")
    @JsonBackReference(value = "technicien-maintenances")
    private Technicien technicien;

    //Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(@NotNull LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public BigDecimal getCout() {
        return cout;
    }

    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Technicien getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }
}