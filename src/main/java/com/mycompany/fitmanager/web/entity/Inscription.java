package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Inscription {
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inscription_id")
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String libelle;

    @NotNull
    @Column(nullable = false)
    private BigDecimal tarif;

    private String description;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    @JsonBackReference(value = "User-inscriptions")
    private Utilisateur utilisateur;

}