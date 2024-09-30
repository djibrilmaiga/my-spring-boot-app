package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.dto.InscriptionDTO;
import com.mycompany.fitmanager.web.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    @Query("SELECT new com.mycompany.fitmanager.web.dto.InscriptionDTO(i.id, i.libelle, i.tarif) FROM Inscription i")
    List<InscriptionDTO> findAllInscription();

    @Query("SELECT new com.mycompany.fitmanager.web.dto.InscriptionDTO(i.id, i.libelle, i.tarif) FROM Inscription i WHERE i.id = :inscriptionId")
    InscriptionDTO findInscriptionById(Integer inscriptionId);
}
