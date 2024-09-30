package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.dto.AbonneDTO;
import com.mycompany.fitmanager.web.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {

    @Query("SELECT new com.mycompany.fitmanager.web.dto.AbonneDTO(pt.seance.id, pt.abonne.id, a.nom, a.prenom, pt.statut) " +
            "FROM Participation pt JOIN pt.abonne a WHERE pt.seance.id = :seanceId")
    List<AbonneDTO> findBySeanceId(@Param("seanceId") Integer seanceId);


    List<Participation> findByAbonneId(Integer abonneId);
}
