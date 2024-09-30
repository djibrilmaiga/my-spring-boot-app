package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mycompany.fitmanager.web.entity.enums.Statut;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abonnement_id")
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Statut statutAbonnement; // Enum(Actif, Inactif)


    @ManyToOne
    @JoinColumn(name = "abonne_id")
    @JsonBackReference(value = "Abonne-abonnements")
    private Abonne abonne;

    // Getters et Setters

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

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Abonne getAbonne() {
        return abonne;
    }

    public void setAbonne(Abonne abonne) {
        this.abonne = abonne;
    }

    public Statut getStatutAbonnement() {
        return statutAbonnement;
    }

    public void setStatutAbonnement(Statut statutAbonnement) {
        this.statutAbonnement = statutAbonnement;
    }

}
