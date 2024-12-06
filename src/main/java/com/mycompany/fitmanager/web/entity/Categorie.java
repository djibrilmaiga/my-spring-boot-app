package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
public class Categorie {
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorie_id")
    private Integer id;

    @Column(nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "categorie", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "categorie-equipements")
    private List<Equipement> equipements = new ArrayList<>();
}