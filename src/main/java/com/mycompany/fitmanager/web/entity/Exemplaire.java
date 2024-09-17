package com.mycompany.fitmanager.web.entity;

import com.mycompany.fitmanager.web.entity.enums.EtatEXemplaire;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exemplaire_id")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String numSerie;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatEXemplaire etat;

    @Column(name = "date_dernier_maintenance")
    private LocalDate dateDernierMaintenance;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipement_id")
    private Equipement equipement;

    // Contient la liste des maintenances effectuées pour un équipement
    @OneToMany(mappedBy = "exemplaire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Maintenance> maintenances = new ArrayList<>();

    // Getters et Setters
    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public EtatEXemplaire getEtat() {
        return etat;
    }

    public void setEtat(EtatEXemplaire etat) {
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
}
