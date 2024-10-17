package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.dto.ExemplaireDTO;
import com.mycompany.fitmanager.web.entity.Exemplaire;
import com.mycompany.fitmanager.web.entity.enums.EtatEXemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
    // Tous les exemplaires d'un équipement
    List<Exemplaire> findAllByEquipementId (Integer equipementId);

    @Query("SELECT new com.mycompany.fitmanager.web.dto.ExemplaireDTO(ex.Id, ex.numSerie, ex.etat, ex.dateDernierMaintenance, e.nom, c.libelle) " +
            "FROM Exemplaire ex JOIN ex.equipement e JOIN e.categorie c")
    List<ExemplaireDTO> findAllExemplairewithEquiAndCat();

    @Query("SELECT COUNT(e) FROM Exemplaire e WHERE e.etat = :etat")
    Integer countExemplairesEnPanne(@Param("etat") EtatEXemplaire etat);

}