package com.mycompany.fitmanager.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class InscriptionDTO {

    private Integer id;
    private String libelle;
    private BigDecimal tarif;
}