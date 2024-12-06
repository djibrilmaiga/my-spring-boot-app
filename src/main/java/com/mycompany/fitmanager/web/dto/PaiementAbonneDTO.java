package com.mycompany.fitmanager.web.dto;

import com.mycompany.fitmanager.web.entity.enums.ModePaiement;
import com.mycompany.fitmanager.web.entity.enums.StatutPaiement;
import com.mycompany.fitmanager.web.entity.enums.TypePaiement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class PaiementAbonneDTO {

    private Integer id;
    private TypePaiement typePaiement;
    private ModePaiement modePaiement;
    private LocalDate datePaiement;
    private StatutPaiement statutPaiement;
    private BigDecimal montantAPayer;
    private BigDecimal montantPaye;
    private BigDecimal montantRestant;
    private String commentaire;
    private Integer abonneId;
    private String nomAbonne;
    private String prenomAbonne;

}