package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mycompany.fitmanager.web.entity.enums.StatutCours;
import com.mycompany.fitmanager.web.entity.enums.TypeSeance;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Seance {
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seance_id")
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String titre;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeSeance typeSeance;

    @NotNull
    @Column(nullable = false)
    private Integer nbreParticipants;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dateHeure;

    @NotNull
    @Column(nullable = false)
    private Integer dureeMinute;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutCours statut; // Enum( Planifié, Terminé )

    @ManyToOne
    @JoinColumn(name = "instructeur_id")
    @JsonBackReference(value = "Instructeur-seances")
    private Instructeur instructeur;

    // Contient la liste des membres inscrits à une seance
    @OneToMany(mappedBy = "seance", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "seance-participations")
    private List<Participation> participations = new ArrayList<>();

}