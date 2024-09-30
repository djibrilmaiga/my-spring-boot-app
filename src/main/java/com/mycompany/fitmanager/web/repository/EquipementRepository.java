package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.dto.EquipementDTO;
import com.mycompany.fitmanager.web.entity.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipementRepository extends JpaRepository<Equipement,Integer> {

    // Tous les équipements par catégorie
    List<Equipement> findAllByCategorieId (Integer categorieId);

    @Query("SELECT new com.mycompany.fitmanager.web.dto.EquipementDTO(e.id, e.nom, e.quantite, c.libelle) " +
            "FROM Equipement e JOIN e.categorie c")
    List<EquipementDTO> findEquipementWithCategorie();
}