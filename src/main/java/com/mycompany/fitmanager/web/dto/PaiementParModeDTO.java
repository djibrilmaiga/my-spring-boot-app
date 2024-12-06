package com.mycompany.fitmanager.web.dto;

import com.mycompany.fitmanager.web.entity.enums.ModePaiement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class PaiementParModeDTO {

    private ModePaiement modePaiement;
    private BigDecimal totalPaiements;
}