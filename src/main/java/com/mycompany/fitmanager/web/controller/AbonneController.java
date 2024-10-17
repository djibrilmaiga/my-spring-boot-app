package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.dto.AbonneSansAbonnementDTO;
import com.mycompany.fitmanager.web.dto.AbonneSelectedDTO;
import com.mycompany.fitmanager.web.entity.Abonne;
import com.mycompany.fitmanager.web.service.AbonneService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/abonne")
@CrossOrigin (origins = "http://localhost:3000")
public class AbonneController {

    @Autowired
    private AbonneService abonneService;

    // POST
    @PostMapping
    public ResponseEntity<Abonne> createAbonne(@RequestBody Abonne abonne){
        Abonne savedAbonne = abonneService.createAbonne(abonne);
        return new ResponseEntity<>(savedAbonne, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Abonne>> getAllAbonnes(){
        List<Abonne> abonnes = abonneService.getAllAbonnes();
        return ResponseEntity.ok(abonnes);
    }

    // GET ALL Abonnés Sans Abonnement Actif
    @GetMapping("/sans-abonnement-actif")
    public ResponseEntity<List<AbonneSansAbonnementDTO>> getAbonnesSansAbonnementActif() {
        List<AbonneSansAbonnementDTO> abonnes = abonneService.getAbonnesSansAbonnementActif();
        return ResponseEntity.ok(abonnes);
    }

    // GET ALL Abonnés (nom, prénom, téléphone)
    @GetMapping("/abonnes-selected")
    public ResponseEntity<List<AbonneSelectedDTO>> getAllAbonneSelected(){
        List<AbonneSelectedDTO> abonnes = abonneService.getAllAbonnesSelected();
        return ResponseEntity.ok(abonnes);
    }

    // GET Nombre d'Abonnés
    @GetMapping("/count")
    public Integer getTotalAbonne(){
        return abonneService.getTotalAbonne();
    }

    // GET ID
    @GetMapping("{id}")
    public ResponseEntity<Abonne> getAbonneById(@PathVariable("id") Integer abonneId){
        Abonne abonne = abonneService.getAbonneById(abonneId);
        return ResponseEntity.ok(abonne);
    }

    // PUT
    @PutMapping("{id}")
    public ResponseEntity<Abonne> updateAbonne(@PathVariable("id") Integer abonneId, @RequestBody Abonne updatedAbonne){
        Abonne abonne = abonneService.updateAbonne(abonneId, updatedAbonne);
        return ResponseEntity.ok(abonne);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAbonne(@PathVariable("id") Integer abonneId){
        abonneService.deleteAbonne(abonneId);
        return ResponseEntity.ok("Abonné supprimé avec succès !");
    }
}
