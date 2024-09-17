package com.mycompany.fitmanager.web.entity;

import com.mycompany.fitmanager.web.entity.enums.TypeSeance;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seance_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeSeance typeSeance;

    @Column(nullable = false)
    private Integer nbreParticipants;

    @Column(nullable = false)
    private LocalDateTime dateHeure;

    @Column(nullable = false)
    private Integer dureeMinute;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructeur_id")
    private Instructeur instructeur; // définit l'instructeur qui anime la séance

    // Contient la liste des membres inscrits à une seance
    @OneToMany(mappedBy = "seance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participation> participations = new ArrayList<>();

   // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeSeance getTypeSeance() {
        return typeSeance;
    }

    public void setTypeSeance(TypeSeance typeSeance) {
        this.typeSeance = typeSeance;
    }

    public Integer getNbreParticipants() {
        return nbreParticipants;
    }

    public void setNbreParticipants(Integer nbreParticipants) {
        this.nbreParticipants = nbreParticipants;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Integer getDureeMinute() {
        return dureeMinute;
    }

    public void setDureeMinute(Integer dureeMinute) {
        this.dureeMinute = dureeMinute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instructeur getInstructeur() {
        return instructeur;
    }

    public void setInstructeur(Instructeur instructeur) {
        this.instructeur = instructeur;
    }
}

