package com.mycompany.fitmanager.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EquipementDTO {

    private Integer id;
    private String nomEquipement;
    private Integer quantite;
    private String nomCategorie;

}