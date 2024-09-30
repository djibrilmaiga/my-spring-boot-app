package com.mycompany.fitmanager.web.dto;

import java.math.BigDecimal;

public class TypeAbonnementDTO {
    private Integer id;
    private String libelle;
    private Integer dureeJour;
    private BigDecimal tarif;

    public TypeAbonnementDTO(Integer id, String libelle, Integer dureeJour, BigDecimal tarif) {
        this.id = id;
        this.libelle = libelle;
        this.dureeJour = dureeJour;
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

    public Integer getDureeJour() {
        return dureeJour;
    }

    public void setDureeJour(Integer dureeJour) {
        this.dureeJour = dureeJour;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }
}
