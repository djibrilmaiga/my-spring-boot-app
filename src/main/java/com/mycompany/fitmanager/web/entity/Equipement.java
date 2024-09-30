package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipement_id")
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String nom;

    @NotNull
    @Column(nullable = false)
    private Integer quantite;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    @JsonBackReference(value = "categorie-equipements")
    private Categorie categorie;

    @OneToMany (mappedBy = "equipement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "equipement-exemplaires")
    private List<Exemplaire> exemplaires = new ArrayList<>();

    // Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull String getNom() {
        return nom;
    }

    public void setNom(@NotNull String nom) {
        this.nom = nom;
    }

    public @NotNull Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(@NotNull Integer quantite) {
        this.quantite = quantite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(List<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }
}