package com.mycompany.fitmanager.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbonneSelectedDTO {

    private Integer id;
    private String nom;
    private String prenom;
    private String telephone;
}
