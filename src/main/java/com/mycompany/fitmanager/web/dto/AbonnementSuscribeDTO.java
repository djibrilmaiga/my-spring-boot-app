package com.mycompany.fitmanager.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor @Getter @Setter
public class AbonnementSuscribeDTO {
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statutAbonnement;
    private Integer typeId;
}
