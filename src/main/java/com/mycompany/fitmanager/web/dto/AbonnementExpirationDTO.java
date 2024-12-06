package com.mycompany.fitmanager.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class AbonnementExpirationDTO {
    private Integer id;
    private LocalDate dateFin;
    private Integer joursRestants;
    private String nom;
    private String prenom;

}