package com.mycompany.fitmanager.web.entity;

import com.mycompany.fitmanager.web.entity.enums.StatutPresence;
import jakarta.persistence.*;

@Entity
public class Participation {

    @EmbeddedId
    private ParticipationId id = new ParticipationId();

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutPresence statut;

    @ManyToOne
    @MapsId("abonneId")
    @JoinColumn(name = "abonne_id", nullable = false)
    private Abonne abonne;

    @ManyToOne
    @MapsId("seanceId")
    @JoinColumn(name = "seance_id", nullable = false)
    private Seance seance;

    // Getters et Setters
    public ParticipationId getId() {
        return id;
    }

    public void setId(ParticipationId id) {
        this.id = id;
    }

    public StatutPresence getStatut() {
        return statut;
    }

    public void setStatut(StatutPresence statut) {
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
