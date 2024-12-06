package com.mycompany.fitmanager.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class MaintenanceDTO {

    private Integer maintenanceId;
    private LocalDate dateMaintenance;
    private BigDecimal coutMaintenance;
    private String description;
    private String nomTechnicen;
    private String prenomTechnicien;
    private String numSerieExemplaire;
    private String nomEquipement;

}