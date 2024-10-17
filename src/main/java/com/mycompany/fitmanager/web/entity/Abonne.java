package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mycompany.fitmanager.web.entity.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity @AllArgsConstructor @NoArgsConstructor
public class Abonne {

    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abonne_id")
    private Integer id;

    @NotNull
    @Size(max = 50)
    @Column(nullable = false)
    private String nom;

    @NotNull
    @Size(max = 50)
    @Column(nullable = false)
    private String prenom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre; // Enum(Homme, Femme)

    @NotNull
    @Pattern(regexp = "^((\\+223)?\\d{8})$", message = "Le numéro de téléphone doit comporter 8 chiffres ou être précédé de l'indicatif +223.")
    @Column(nullable = false, unique = true)
    private String telephone;

    private String email;

    @Column(nullable = false)
    private LocalDate dateInscription;

    @Column(nullable = false)
    private BigDecimal paiementTotal;

    // Liste des abonnements d'un Membre
    @OneToMany(mappedBy = "abonne", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "Abonne-abonnements")
    private List<Abonnement> abonnements = new ArrayList<>();

    // Liste des paiements d'un membre
    @OneToMany(mappedBy = "abonne", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "Abonne-paiements")
    private List<Paiement> paiements = new ArrayList<>();

    // Liste des participations aux seances d'un membre
    @OneToMany(mappedBy = "abonne", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "abonne-participations")
    private List<Participation> participations = new ArrayList<>();

   // Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull @Size(max = 50) String getNom() {
        return nom;
    }

    public void setNom(@NotNull @Size(max = 50) String nom) {
        this.nom = nom;
    }

    public @NotNull @Size(max = 50) String getPrenom() {
        return prenom;
    }

    public void setPrenom(@NotNull @Size(max = 50) String prenom) {
        this.prenom = prenom;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public @NotNull @Pattern(regexp = "^((\\+223)?\\d{8})$", message = "Le numéro de téléphone doit comporter 8 chiffres ou être précédé de l'indicatif +223.") String getTelephone() {
        return telephone;
    }

    public void setTelephone(@NotNull @Pattern(regexp = "^((\\+223)?\\d{8})$", message = "Le numéro de téléphone doit comporter 8 chiffres ou être précédé de l'indicatif +223.") String telephone) {
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

    public BigDecimal getPaiementTotal() {
        return paiementTotal;
    }

    public void setPaiementTotal(BigDecimal paiementTotal) {
        this.paiementTotal = paiementTotal;
    }

    public List<Abonnement> getAbonnements() {
        return abonnements;
    }

    public void setAbonnements(List<Abonnement> abonnements) {
        this.abonnements = abonnements;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }
}

