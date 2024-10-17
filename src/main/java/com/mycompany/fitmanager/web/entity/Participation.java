package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mycompany.fitmanager.web.entity.enums.StatutPresence;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @AllArgsConstructor @NoArgsConstructor
public class Participation {

    @EmbeddedId
    private ParticipationId id = new ParticipationId();

    @Enumerated(EnumType.STRING)
    private StatutPresence statut; // Enum(Present, Absent)

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("abonneId")
    @JoinColumn(name = "abonne_id")
    @JsonBackReference(value = "abonne-participations")
    private Abonne abonne;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("seanceId")
    @JoinColumn(name = "seance_id")
    @JsonBackReference(value = "seance-participations")
    private Seance seance;

    // Getters et Setters

    public ParticipationId getId() {
        return id;
    }

    public void setId(ParticipationId id) {
        this.id = id;
    }

    public  StatutPresence getStatut() {
        return statut;
    }

    public void setStatut( StatutPresence statut) {
        this.statut = statut;
    }

    public Abonne getAbonne() {
        return abonne;
    }

    public void setAbonne(Abonne abonne) {
        this.abonne = abonne;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }
}
