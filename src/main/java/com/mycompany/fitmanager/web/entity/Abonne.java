package com.mycompany.fitmanager.web.entity;

import com.mycompany.fitmanager.web.entity.enums.Genre;
import com.mycompany.fitmanager.web.entity.enums.Statut;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Abonne {

    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abonne_id")
    private Integer id;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String nom;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String prenom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre; // Enum(Homme, Femme, Autre)

    @NotNull
    @Pattern(regexp = "^((\\+223)?\\d{8})$", message = "Le numéro de téléphone doit comporter 8 chiffres ou être précédé de l'indicatif +223.")
    @Column(nullable = false, unique = true)
    private String telephone;

    private String email;

    @Column(nullable = false)
    private LocalDate dateInscription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Statut statutAbonnement; // Enum(Actif, Inactif) - Par défaut est Inactif et devient actif si l'abonné a pris un abonnement en cours de validité

    @Column(nullable = false)
    private BigDecimal paiementTotal; // Par défaut Zero et s'additionne par le montant payé par l'abonné à chaque paiement

    // Liste d'éléments liés à un Membre
    // Liste des abonnements d'un Membre
    @OneToMany(mappedBy = "abonne", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Abonnement> abonnements = new ArrayList<>();

    // Liste des paiements d'un membre
    @OneToMany(mappedBy = "abonne", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paiement> paiements = new ArrayList<>();

    // Liste des participations aux seances d'un membre
    @OneToMany(mappedBy = "abonne", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participation> participations = new ArrayList<>();

    // Constructeurs
    public Abonne() {
    }
    public Abonne(String nom, String prenom, Genre genre, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.telephone = telephone;
        this.dateInscription = LocalDate.now();
        this.statutAbonnement = Statut.Inactif;
        this.paiementTotal = BigDecimal.ZERO;
    }
    public Abonne(String nom, String prenom, Genre genre, String telephone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.telephone = telephone;
        this.email = email;
        this.dateInscription = LocalDate.now();
        this.statutAbonnement = Statut.Inactif;
        this.paiementTotal = BigDecimal.ZERO;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Statut getStatut() {
        return statutAbonnement;
    }

    public void setStatut(Statut statut) {
        this.statutAbonnement = statut;
    }

    public BigDecimal getPaiementTotal() {
        return paiementTotal;
    }

    public void setPaiementTotal(BigDecimal paiementTotal) {
        this.paiementTotal = paiementTotal;
    }

    public List<Abonnement> getAbonnements() {
        return abonnements;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    // Méthodes Utilitaires
    public Boolean isAbonnementActif(){
        return this.statutAbonnement == Statut.Actif;
    }

    public void ajouterAbonnement(Abonnement abonnement){
        abonnements.add(abonnement);
        abonnement.setAbonne(this);
    }

    public void supprimerAbonnement(Abonnement abonnement){
        abonnements.remove(abonnement);
        abonnement.setAbonne(null);
    }

    public void ajouterPaiement(Paiement paiement){
        paiements.add(paiement);
        paiement.setAbonne(this);
    }

    public void supprimerAbonnement(Paiement paiement){
        paiements.remove(paiement);
        paiement.setAbonne(null);
    }
}
