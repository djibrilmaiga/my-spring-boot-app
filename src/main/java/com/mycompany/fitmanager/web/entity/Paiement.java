package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mycompany.fitmanager.web.entity.enums.ModePaiement;
import com.mycompany.fitmanager.web.entity.enums.StatutPaiement;
import com.mycompany.fitmanager.web.entity.enums.TypePaiement;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paiement_id")
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypePaiement typePaiement; // Enum (Inscription, Abonnement, Seance)

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModePaiement modePaiement;  // Enum (Espèce, Mobile Money)

    @NotNull
    @Column(nullable = false)
    private LocalDate datePaiement;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutPaiement statutPaiement; // Enum(Partiel, Complet)

    @NotNull
    @Column(nullable = false)
    private BigDecimal  montantAPayer;

    @NotNull
    @Column(nullable = false)
    private BigDecimal montantPaye;

    @NotNull
    @Column(nullable = false)
    private BigDecimal montantRestant;

    @Column(columnDefinition = "TEXT")
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "abonne_id", nullable = false)
    @JsonBackReference(value = "Abonne-paiements")
    private Abonne abonne;

    // Getters et Setters

    public @NotNull TypePaiement getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(@NotNull TypePaiement typePaiement) {
        this.typePaiement = typePaiement;
    }

    public @NotNull ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(@NotNull ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public @NotNull LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(@NotNull LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public @NotNull StatutPaiement getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(@NotNull StatutPaiement statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    public @NotNull BigDecimal getMontantAPayer() {
        return montantAPayer;
    }

    public void setMontantAPayer(@NotNull BigDecimal montantAPayer) {
        this.montantAPayer = montantAPayer;
    }

    public @NotNull BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(@NotNull BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public @NotNull BigDecimal getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(@NotNull BigDecimal montantRestant) {
        this.montantRestant = montantRestant;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Abonne getAbonne() {
        return abonne;
    }

    public void setAbonne(Abonne abonne) {
        this.abonne = abonne;
    }
}
