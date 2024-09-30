package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mycompany.fitmanager.web.entity.enums.StatutCours;
import com.mycompany.fitmanager.web.entity.enums.TypeSeance;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seance_id")
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String titre;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeSeance typeSeance;

    @NotNull
    @Column(nullable = false)
    private Integer nbreParticipants;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dateHeure;

    @NotNull
    @Column(nullable = false)
    private Integer dureeMinute;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutCours statut;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructeur_id")
    @JsonBackReference(value = "Instructeur-seances")
    private Instructeur instructeur;

    // Contient la liste des membres inscrits à une seance
    @OneToMany(mappedBy = "seance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "seance-participations")
    private List<Participation> participations = new ArrayList<>();

    // Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull String getTitre() {
        return titre;
    }

    public void setTitre(@NotNull String titre) {
        this.titre = titre;
    }

    public @NotNull TypeSeance getTypeSeance() {
        return typeSeance;
    }

    public void setTypeSeance(@NotNull TypeSeance typeSeance) {
        this.typeSeance = typeSeance;
    }

    public @NotNull Integer getNbreParticipants() {
        return nbreParticipants;
    }

    public void setNbreParticipants(@NotNull Integer nbreParticipants) {
        this.nbreParticipants = nbreParticipants;
    }

    public @NotNull LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(@NotNull LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public @NotNull Integer getDureeMinute() {
        return dureeMinute;
    }

    public void setDureeMinute(@NotNull Integer dureeMinute) {
        this.dureeMinute = dureeMinute;
    }

    public @NotNull String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    public StatutCours getStatut() {
        return statut;
    }

    public void setStatut(StatutCours statut) {
        this.statut = statut;
    }

    public Instructeur getInstructeur() {
        return instructeur;
    }

    public void setInstructeur(Instructeur instructeur) {
        this.instructeur = instructeur;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }
}

