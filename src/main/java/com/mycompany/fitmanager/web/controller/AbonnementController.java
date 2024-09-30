package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.dto.AbonnementDTO;
import com.mycompany.fitmanager.web.entity.Abonne;
import com.mycompany.fitmanager.web.entity.Abonnement;
import com.mycompany.fitmanager.web.service.AbonnementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/abonnement")
@CrossOrigin(origins = "http://localhost:3000")
public class AbonnementController {

    private AbonnementService abonnementService;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<AbonnementDTO>> getAllAbonnementWithAbonneInf(){
        List<AbonnementDTO> abonnements = abonnementService.getAllAbonnementWithAbonneInf();
        return ResponseEntity.ok(abonnements);
    }
}
