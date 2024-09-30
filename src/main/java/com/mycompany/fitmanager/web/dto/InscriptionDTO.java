package com.mycompany.fitmanager.web.dto;

import java.math.BigDecimal;

public class InscriptionDTO {

    private Integer id;
    private String libelle;
    private BigDecimal tarif;

    public InscriptionDTO(Integer id, String libelle, BigDecimal tarif) {
        this.id = id;
        this.libelle = libelle;
        this.tarif = tarif;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }
}
