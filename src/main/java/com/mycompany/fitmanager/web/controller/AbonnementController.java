package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.dto.AbonnementDTO;
import com.mycompany.fitmanager.web.dto.AbonnementExpirationDTO;
import com.mycompany.fitmanager.web.dto.AbonnementSuscribeDTO;
import com.mycompany.fitmanager.web.entity.Abonnement;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
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

    // POST
    @PostMapping("/abonne/{abonneId}")
    public ResponseEntity<String> createAbonnement(@PathVariable("abonneId") Integer abonneId,@RequestBody AbonnementSuscribeDTO newAbonnement){
        try {
            String rs = abonnementService.createAbonnement(abonneId, newAbonnement);
            if ("L'abonné a encore un abonnement en cours.".equals(rs)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(rs);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(rs);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création de l'abonnement.");
        }
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<AbonnementDTO>> getAllAbonnementWithAbonneInf(){
        List<AbonnementDTO> abonnements = abonnementService.getAllAbonnementWithAbonneInf();
        return ResponseEntity.ok(abonnements);
    }

    // GET ALL Abonnements Actifs sans expiration
    @GetMapping("/proche-expiration")
    public List<AbonnementExpirationDTO> getAbonnementsProchesExpiration() {
        return abonnementService.getAbonnementsProchesExpiration(7);
    }
}