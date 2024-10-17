package com.mycompany.fitmanager.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ParticipationId implements Serializable {

    @Column(name = "abonne_id")
    private Integer abonneId;

    @Column(name = "seance_id")
    private Integer seanceId;

    // Getters et Setters
    public Integer getAbonneId() {
        return abonneId;
    }

    public void setAbonneId(Integer membreId) {
        this.abonneId = membreId;
    }

    public Integer getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(Integer seanceId) {
        this.seanceId = seanceId;
    }
}
