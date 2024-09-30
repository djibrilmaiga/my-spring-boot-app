package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Instructeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructeur_id")
    private Integer id;

    @NotNull
    @Size(max=50)
    @Column(nullable = false)
    private String nom;

    @NotNull
    @Size(max=50)
    @Column(nullable = false)
    private String prenom;

    @NotNull
    @Column(nullable = false)
    private String telephone;

    @NotNull
    @Column(nullable = false)
    private String specialite;

    // Contient la liste des séances animées par un instructeur
    @OneToMany(mappedBy = "instructeur")
    @JsonManagedReference(value = "Instructeur-seances")
    private List<Seance> seances = new ArrayList<>();

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

    public @NotNull String getTelephone() {
        return telephone;
    }

    public void setTelephone(@NotNull String telephone) {
        this.telephone = telephone;
    }

    public @NotNull String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(@NotNull String specialite) {
        this.specialite = specialite;
    }

    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }
}
