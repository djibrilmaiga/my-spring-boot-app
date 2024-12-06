package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mycompany.fitmanager.web.entity.enums.StatutPresence;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Participation {
    // Propriétés
    @EmbeddedId
    private ParticipationId id = new ParticipationId();

    @Enumerated(EnumType.STRING)
    private StatutPresence statut; // Enum(Present, Absent)

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("abonneId")
    @JoinColumn(name = "abonne_id")
    @JsonBackReference(value = "abonne-participations")
    private Abonne abonne;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("seanceId")
    @JoinColumn(name = "seance_id")
    @JsonBackReference(value = "seance-participations")
    private Seance seance;
}