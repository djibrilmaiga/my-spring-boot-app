package com.mycompany.fitmanager.web.entity;

import com.mycompany.fitmanager.web.entity.enums.Statut;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Abonnement {
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abonnement_id")
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Column(length = 500)
    private String commentaire;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Statut statut;

    @ManyToOne ( cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "abonne_id")
    private Abonne abonne;

    // Constructeurs
    public Abonnement(){
    }
    public Abonnement(@NotNull LocalDate dateDebut, Service service, Abonne abonne, String commentaire) {
        this.dateDebut = dateDebut;
        this.service = service;
        this.abonne = abonne;
        this.commentaire = commentaire;
        if (this.dateDebut != null && this.service != null) {
            this.dateFin = this.dateDebut.plusDays(service.getDureeJour());
            this.statut = Statut.Actif;
        }
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        calculerDateFin();
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
        calculerDateFin();
    }

    public Abonne getAbonne() {
        return abonne;
    }

    public void setAbonne(Abonne abonne) {
        this.abonne = abonne;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public void calculerDateFin() {
        if (this.dateDebut != null && this.service != null) {
            this.dateFin = this.dateDebut.plusDays(service.getDureeJour());
        }
    }
}
