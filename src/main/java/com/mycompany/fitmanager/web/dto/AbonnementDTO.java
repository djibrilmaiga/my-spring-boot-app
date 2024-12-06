package com.mycompany.fitmanager.web.dto;

import com.mycompany.fitmanager.web.entity.enums.Statut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class AbonnementDTO {
    private Integer abonnementId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Statut statutAbonnement;
    private String nomAbonne;
    private String prenomAbonne;

}
