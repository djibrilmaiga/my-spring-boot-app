package com.mycompany.fitmanager.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class AbonneSansAbonnementDTO {
    private Integer id;
    private String nom;
    private String prenom;
    private String telephone;
    private LocalDate derniereDateFinAbonnement;
}

