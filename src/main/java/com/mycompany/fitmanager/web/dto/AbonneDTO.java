package com.mycompany.fitmanager.web.dto;

import com.mycompany.fitmanager.web.entity.enums.StatutPresence;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class AbonneDTO {

    private Integer seanceId;
    private Integer abonneId;
    private String nom;
    private String prenom;
    private StatutPresence statut;

}