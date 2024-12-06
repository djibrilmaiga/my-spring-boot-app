package com.mycompany.fitmanager.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class TypeAbonnementDTO {
    private Integer id;
    private String libelle;
    private Integer dureeJour;
    private BigDecimal tarif;

}