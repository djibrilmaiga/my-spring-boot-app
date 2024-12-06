package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mycompany.fitmanager.web.entity.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor // Constructeur sans arguments
@AllArgsConstructor // Constructeur avec tous les arguments
@Getter // Génère automatiquement les Getters
@Setter // Génère automatiquement les Setters
@Entity // Annotation Bean tenue compte dans le Contexte de Spring - Inversion de contrôle (IoC)
public class Abonne {
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID est AUTO INCREMENT dans la Base de Données
    @Column(name = "abonne_id") // Correspondance du nom de Champs dans la BD
    private Integer id;

    @NotNull // Validator pour dire qua la propriété est Non Null
    @Column(nullable = false)
    private String nom;

    @NotNull
    @Column(nullable = false)
    private String prenom;

    @Enumerated(EnumType.STRING) // Dans la BD, le type est un Enum
    @Column(nullable = false)
    private Genre genre; // Enum(Homme, Femme)

    @NotNull
    @Pattern(regexp = "^((\\+223)?\\d{8})$",
            message = "Le numéro de téléphone doit comporter 8 chiffres ou être précédé de l'indicatif +223.")
    @Column(nullable = false, unique = true)
    private String telephone;

    private String email; // Noter que l'email peut être NULL

    @Column(nullable = false)
    private LocalDate dateInscription;

    @Column(nullable = false)
    private BigDecimal paiementTotal;

    // Liste des abonnements d'un Membre
    @OneToMany(mappedBy = "abonne", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "Abonne-abonnements")
    private List<Abonnement> abonnements = new ArrayList<>();

    // Liste des paiements d'un membre
    @OneToMany(mappedBy = "abonne", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "Abonne-paiements")
    private List<Paiement> paiements = new ArrayList<>();

    // Liste des participations aux seances d'un membre
    @OneToMany(mappedBy = "abonne", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "abonne-participations")
    private List<Participation> participations = new ArrayList<>();

   // Getters et Setters
    public @NotNull @Pattern(regexp = "^((\\+223)?\\d{8})$", message = "Le numéro de téléphone doit comporter 8 chiffres ou être précédé de l'indicatif +223.") String getTelephone() {
        return telephone;
    }

    public void setTelephone(@NotNull @Pattern(regexp = "^((\\+223)?\\d{8})$", message = "Le numéro de téléphone doit comporter 8 chiffres ou être précédé de l'indicatif +223.") String telephone) {
        this.telephone = telephone;
    }

}