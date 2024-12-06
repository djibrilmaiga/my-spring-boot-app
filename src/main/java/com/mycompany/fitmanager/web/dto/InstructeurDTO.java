package com.mycompany.fitmanager.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InstructeurDTO {

    private Integer instructeurId;
    private String nom;
    private String prenom;
    private String telephone;
    private String specialite;

}