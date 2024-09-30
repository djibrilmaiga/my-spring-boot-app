package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mycompany.fitmanager.web.entity.enums.StatutPresence;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participation {

    @EmbeddedId
    private ParticipationId id = new ParticipationId();

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutPresence statut;

    @ManyToOne
    @MapsId("abonneId")
    @JoinColumn(name = "abonne_id")
    private Abonne abonne;

    @ManyToOne
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

    public @NotNull StatutPresence getStatut() {
        return statut;
    }

    public void setStatut(@NotNull StatutPresence statut) {
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
