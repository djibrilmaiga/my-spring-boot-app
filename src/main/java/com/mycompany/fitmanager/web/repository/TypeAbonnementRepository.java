package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.dto.TypeAbonnementDTO;
import com.mycompany.fitmanager.web.entity.TypeAbonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeAbonnementRepository extends JpaRepository<TypeAbonnement, Integer> {

    @Query("SELECT new com.mycompany.fitmanager.web.dto.TypeAbonnementDTO(ta.id, ta.libelle, ta.dureeJour, ta.tarif) FROM TypeAbonnement ta")
    List<TypeAbonnementDTO> findAllType();

    @Query("SELECT new com.mycompany.fitmanager.web.dto.TypeAbonnementDTO(ta.id, ta.libelle, ta.dureeJour, ta.tarif) FROM TypeAbonnement ta WHERE ta.id = :typeId")
    TypeAbonnementDTO findTypeAbonnementById(@Param("typeId") Integer typeId);
}
