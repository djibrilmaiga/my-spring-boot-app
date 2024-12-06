package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Equipement {
    // Propriétés
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

    @OneToMany (mappedBy = "equipement", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "equipement-exemplaires")
    private List<Exemplaire> exemplaires = new ArrayList<>();
}