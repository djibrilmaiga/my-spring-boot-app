package com.mycompany.fitmanager.web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class TypeAbonnement {
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_abonnement_id")
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String libelle;

    @NotNull
    @Column(nullable = false)
    private Integer dureeJour;

    @NotNull
    @Column(nullable = false)
    private BigDecimal tarif;

    @Column(columnDefinition = "TEXT")
    private String description;

}