package com.mycompany.fitmanager.web.dto;

import com.mycompany.fitmanager.web.entity.Paiement;
import com.mycompany.fitmanager.web.entity.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @Getter @Setter
public class AbonneSuscribeDTO {
    private String nom;
    private String prenom;
    private Genre genre;
    private String telephone;
    private String email;
    private LocalDate dateInscription;
    private BigDecimal paiementTotal;
    private List<Paiement> paiements;
    private List<AbonnementSuscribeDTO> abonnements;
}
