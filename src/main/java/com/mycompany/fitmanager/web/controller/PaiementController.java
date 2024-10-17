package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.dto.PaiementAbonneDTO;
import com.mycompany.fitmanager.web.dto.PaiementParModeDTO;
import com.mycompany.fitmanager.web.entity.Paiement;
import com.mycompany.fitmanager.web.service.PaiementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/paiement")
@CrossOrigin(origins = "http://localhost:3000")
public class PaiementController {

    private PaiementService paiementService;


    // POST
    @PostMapping("/abonne/{abonneId}")
    public ResponseEntity<Paiement> createPaiement(@PathVariable Integer abonneId,@RequestBody Paiement paiement){
        Paiement savedPaiement = paiementService.createPaiement(abonneId, paiement);
        return new ResponseEntity<>(paiement, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<PaiementAbonneDTO>> getAllPaiementsWithAbonneInfo(){
        List<PaiementAbonneDTO> paiements = paiementService.getAllPaiementWithAbonneInfo();
        return ResponseEntity.ok(paiements);
    }

    // GET ID
    @GetMapping("{id}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable("id") Integer paiementId){
        Paiement paiement = paiementService.getPaiementById(paiementId);
        return ResponseEntity.ok(paiement);
    }

    @GetMapping("/paiement-abonne/{id}")
    public ResponseEntity<PaiementAbonneDTO> getPaiementAbonneById(@PathVariable("id") Integer paiementId){
        PaiementAbonneDTO paiement = paiementService.getPaiementAbonneById(paiementId);
        return ResponseEntity.ok(paiement);
    }

    // GET MONTANT TOTAL PAR MOIS
    @GetMapping("/somme")
    public BigDecimal getSommePaiements(@RequestParam Integer year, @RequestParam Integer month){
        return paiementService.getSommePaiementsByMonth(year, month);
    }

    // GET PAIEMENTS PAR MODE
    @GetMapping("/repartition")
    public List<PaiementParModeDTO> getRepartitionPaiementsParMode(){
        return paiementService.getTotalPaiementsByModePaiement();
    }

    // PUT
    @PutMapping("{id}")
    public ResponseEntity<Paiement> updatePaiement(@PathVariable("id") Integer paiementId, @RequestBody Paiement newPaiement){
        Paiement paiement = paiementService.updatePaiement(paiementId, newPaiement);
        return ResponseEntity.ok(paiement);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePaiement(@PathVariable("id") Integer paiementId){
        paiementService.deletePaiement(paiementId);
        return ResponseEntity.ok("Paiement supprimé avec succès !");
    }
}
