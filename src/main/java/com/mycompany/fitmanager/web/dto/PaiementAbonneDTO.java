package com.mycompany.fitmanager.web.dto;

import com.mycompany.fitmanager.web.entity.enums.ModePaiement;
import com.mycompany.fitmanager.web.entity.enums.StatutPaiement;
import com.mycompany.fitmanager.web.entity.enums.TypePaiement;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaiementAbonneDTO {

    private Integer id;
    private TypePaiement typePaiement;
    private ModePaiement modePaiement;
    private LocalDate datePaiement;
    private StatutPaiement statutPaiement;
    private BigDecimal montantAPayer;
    private BigDecimal montantPaye;
    private BigDecimal montantRestant;
    private String commentaire;
    private Integer abonneId;
    private String nomAbonne;
    private String prenomAbonne;

    // Constructors
    public PaiementAbonneDTO(Integer paiementId, TypePaiement typePaiement, ModePaiement modePaiement, LocalDate datePaiement, StatutPaiement statutPaiement, BigDecimal montantAPayer, BigDecimal montantPaye, BigDecimal montantRestant, String commentaire, Integer abonneId, String nomAbonne, String prenomAbonne) {
        this.id = paiementId;
        this.typePaiement = typePaiement;
        this.modePaiement = modePaiement;
        this.datePaiement = datePaiement;
        this.statutPaiement = statutPaiement;
        this.montantAPayer = montantAPayer;
        this.montantPaye = montantPaye;
        this.montantRestant = montantRestant;
        this.commentaire = commentaire;
        this.abonneId = abonneId;
        this.nomAbonne = nomAbonne;
        this.prenomAbonne = prenomAbonne;
    }

    // Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypePaiement getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(TypePaiement typePaiement) {
        this.typePaiement = typePaiement;
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public StatutPaiement getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(StatutPaiement statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    public BigDecimal getMontantAPayer() {
        return montantAPayer;
    }

    public void setMontantAPayer(BigDecimal montantAPayer) {
        this.montantAPayer = montantAPayer;
    }

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public BigDecimal getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(BigDecimal montantRestant) {
        this.montantRestant = montantRestant;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Integer getAbonneId() {
        return abonneId;
    }

    public void setAbonneId(Integer abonneId) {
        this.abonneId = abonneId;
    }

    public String getNomAbonne() {
        return nomAbonne;
    }

    public void setNomAbonne(String nomAbonne) {
        this.nomAbonne = nomAbonne;
    }

    public String getPrenomAbonne() {
        return prenomAbonne;
    }

    public void setPrenomAbonne(String prenomAbonne) {
        this.prenomAbonne = prenomAbonne;
    }
}
