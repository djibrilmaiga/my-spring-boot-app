package com.mycompany.fitmanager.web.dto;

import com.mycompany.fitmanager.web.entity.enums.EtatEXemplaire;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class ExemplaireDTO {

    private Integer exemplaireId;
    private String numSerie;
    private EtatEXemplaire etat;
    private LocalDate dateDernierMaintenance;
    private String nomEquipement;
    private String nomCategorie;
}