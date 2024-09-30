package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance, Integer> {
    // Tous les seances animés par un instructeur
    List<Seance> findByInstructeurId(Integer instructeurId);

}
