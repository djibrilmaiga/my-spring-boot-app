package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mycompany.fitmanager.web.entity.enums.ModePaiement;
import com.mycompany.fitmanager.web.entity.enums.StatutPaiement;
import com.mycompany.fitmanager.web.entity.enums.TypePaiement;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Paiement {
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paiement_id")
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypePaiement typePaiement; // Enum (Inscription, Abonnement, Seance)

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModePaiement modePaiement;  // Enum (Espèce, Mobile Money)

    @NotNull
    @Column(nullable = false)
    private LocalDate datePaiement;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutPaiement statutPaiement; // Enum(Partiel, Complet)

    @NotNull
    @Column(nullable = false)
    private BigDecimal  montantAPayer;

    @NotNull
    @Column(nullable = false)
    private BigDecimal montantPaye;

    @NotNull
    @Column(nullable = false)
    private BigDecimal montantRestant;

    @Column(columnDefinition = "TEXT")
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "abonne_id", nullable = false)
    @JsonBackReference(value = "Abonne-paiements")
    private Abonne abonne;
}