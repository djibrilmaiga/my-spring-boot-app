package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.dto.CoachDTO;
import com.mycompany.fitmanager.web.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance, Integer> {
    // Tous les seances animés par un instructeur
    List<Seance> findByInstructeurId(Integer instructeurId);

    @Query("SELECT new com.mycompany.fitmanager.web.dto.CoachDTO(i.nom, i.prenom) " +
            "FROM Seance s JOIN s.instructeur i WHERE s.id = :seanceId")
    CoachDTO findInstructeurBySeanceId(@Param("seanceId") Integer seanceId);

}
