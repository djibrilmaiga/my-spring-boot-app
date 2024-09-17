package com.mycompany.fitmanager.web.entity;

import com.mycompany.fitmanager.web.entity.enums.ModePaiement;
import com.mycompany.fitmanager.web.entity.enums.StatutPaiement;
import com.mycompany.fitmanager.web.entity.enums.TypePaiement;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paiement_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypePaiement typePaiement; // Enum (Inscription, Abonnement, Seance)

    @Column(nullable = false)
    private BigDecimal  montantTotal;

    @NotNull
    @Column(nullable = false)
    private BigDecimal montantPaye;

    @Column(nullable = false)
    private BigDecimal montantRestant;

    @Column(nullable = false)
    private LocalDate datePaiement;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;  // Enum (Espèce, Mobile Money)

    @Enumerated(EnumType.STRING)
    private StatutPaiement statutPaiement; // Enum(Partiel, Complet)

    private LocalDate dateEcheance; //Définit si montantRestant != 0.0

    @Column(columnDefinition = "TEXT")
    private String commentaire;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id", unique = true)
    private Service service;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "abonne_id", nullable = false)
    private Abonne abonne;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "seance_id")
    private Seance seance;

    //Constructeurs
    public Paiement() {
    }

    public Paiement(TypePaiement typePaiement,  ModePaiement modePaiement, BigDecimal montantPaye) {
        this.typePaiement = typePaiement;
        this.modePaiement = modePaiement;
        this.montantPaye = montantPaye;
    }

    //Getters et Setters
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

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
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

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public StatutPaiement getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(StatutPaiement statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Abonne getAbonne() {
        return abonne;
    }

    public void setAbonne(Abonne abonne) {
        this.abonne = abonne;
    }
}
