package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mycompany.fitmanager.web.entity.enums.Statut;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Abonnement {
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abonnement_id")
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Statut statutAbonnement; // Enum(Actif, Inactif)

    @ManyToOne
    @JoinColumn(name = "abonne_id")
    @JsonBackReference(value = "Abonne-abonnements")
    private Abonne abonne;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonBackReference(value = "Type-abonnements")
    private TypeAbonnement type;
}