package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Maintenance {
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_id")
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private BigDecimal cout;

    @Column(columnDefinition = "TEXT")
    private String rapport;

    @ManyToOne
    @JoinColumn(name = "exemplaire_id")
    @JsonBackReference(value = "exemplaire-maintenances")
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "technicien_id")
    @JsonBackReference(value = "technicien-maintenances")
    private Technicien technicien;
}