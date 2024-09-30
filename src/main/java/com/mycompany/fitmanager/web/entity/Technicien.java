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
public class Technicien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technicien_id")
    private Integer id;

    @NotNull
    @Column(nullable = false)
    @Size(max = 50)
    private String nom;

    @NotNull
    @Size(max = 50)
    @Column(nullable = false)
    private String prenom;

    @NotNull
    @Size(max = 15)
    @Column(nullable = false)
    private String telephone;

    @OneToMany(mappedBy = "technicien")
    @JsonManagedReference(value = "technicien-maintenances")
    private List<Maintenance> maintenances = new ArrayList<>();

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

    public @NotNull @Size(max = 15) String getTelephone() {
        return telephone;
    }

    public void setTelephone(@NotNull @Size(max = 15) String telephone) {
        this.telephone = telephone;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }
}
