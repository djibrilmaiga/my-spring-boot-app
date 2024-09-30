package com.mycompany.fitmanager.web.dto;

import com.mycompany.fitmanager.web.entity.enums.ModePaiement;

import java.math.BigDecimal;

public class PaiementParModeDTO {

    private ModePaiement modePaiement;
    private BigDecimal totalPaiements;

    // Constructors
    public PaiementParModeDTO(ModePaiement modePaiement, BigDecimal totalPaiements) {
        this.modePaiement = modePaiement;
        this.totalPaiements = totalPaiements;
    }

    // Getters et Setters
    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public BigDecimal getTotalPaiements() {
        return totalPaiements;
    }

    public void setTotalPaiements(BigDecimal totalPaiements) {
        this.totalPaiements = totalPaiements;
    }
}
