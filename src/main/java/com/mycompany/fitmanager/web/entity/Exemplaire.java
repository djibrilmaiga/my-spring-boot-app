package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mycompany.fitmanager.web.entity.enums.EtatEXemplaire;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Exemplaire {
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exemplaire_id")
    private Integer id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String numSerie;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatEXemplaire etat;

    @Column(name = "date_dernier_maintenance")
    private LocalDate dateDernierMaintenance;

    @ManyToOne
    @JoinColumn(name = "equipement_id")
    @JsonBackReference(value = "equipement-exemplaires")
    private Equipement equipement;

    // Contient la liste des maintenances effectuées pour un équipement
    @OneToMany(mappedBy = "exemplaire", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "exemplaire-maintenances")
    private List<Maintenance> maintenances = new ArrayList<>();

}