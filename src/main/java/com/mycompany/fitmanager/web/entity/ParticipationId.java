package com.mycompany.fitmanager.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class ParticipationId implements Serializable {
    // Propriétés
    @Column(name = "abonne_id")
    private Integer abonneId;

    @Column(name = "seance_id")
    private Integer seanceId;

    // Implémentation de la méthode equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipationId that = (ParticipationId) o;
        return Objects.equals(abonneId, that.abonneId) &&
                Objects.equals(seanceId, that.seanceId);
    }

    // Implémentation de la méthode hashCode
    @Override
    public int hashCode() {
        return Objects.hash(abonneId, seanceId);
    }

}