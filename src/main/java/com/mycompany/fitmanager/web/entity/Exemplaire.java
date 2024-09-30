package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mycompany.fitmanager.web.entity.enums.EtatEXemplaire;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exemplaire_id")
    private Integer id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String numSerie;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatEXemplaire etat;

    @Column(name = "date_dernier_maintenance")
    private LocalDate dateDernierMaintenance;

    @ManyToOne
    @JoinColumn(name = "equipement_id")
    @JsonBackReference(value = "equipement-exemplaires")
    private Equipement equipement;

    // Contient la liste des maintenances effectuées pour un équipement
    @OneToMany(mappedBy = "exemplaire", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "exemplaire-maintenances")
    private List<Maintenance> maintenances = new ArrayList<>();

    // Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(@NotNull String numSerie) {
        this.numSerie = numSerie;
    }

    public @NotNull EtatEXemplaire getEtat() {
        return etat;
    }

    public void setEtat(@NotNull EtatEXemplaire etat) {
        this.etat = etat;
    }

    public LocalDate getDateDernierMaintenance() {
        return dateDernierMaintenance;
    }

    public void setDateDernierMaintenance(LocalDate dateDernierMaintenance) {
        this.dateDernierMaintenance = dateDernierMaintenance;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }
}