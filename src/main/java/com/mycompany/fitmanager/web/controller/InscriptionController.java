package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.dto.InscriptionDTO;
import com.mycompany.fitmanager.web.entity.Inscription;
import com.mycompany.fitmanager.web.service.InscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/inscription")
@CrossOrigin(origins = "http://localhost:3000")
public class InscriptionController {

    private InscriptionService inscriptionService;

    // POST
    @PostMapping
    public ResponseEntity<Inscription> createInscription(@RequestBody Inscription inscription){
        Inscription savedInscription = inscriptionService.createInscription(inscription);
        return new ResponseEntity<>(savedInscription, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<InscriptionDTO>> getAllInscription(){
        List<InscriptionDTO> inscriptions = inscriptionService.getAllInscription();
        return ResponseEntity.ok(inscriptions);
    }

    // GET ID
    @GetMapping("{id}")
    public ResponseEntity<InscriptionDTO> getInscriptionById(@PathVariable("id") Integer inscriptionId){
        InscriptionDTO inscription = inscriptionService.getInscriptionById(inscriptionId);
        return ResponseEntity.ok(inscription);
    }

    // PUT
    @PutMapping("{id}")
    public ResponseEntity<Inscription> updateInscription(@PathVariable("id") Integer inscriptionId,@RequestBody Inscription newInscription){
        Inscription inscription = inscriptionService.updateInscription(inscriptionId, newInscription);
        return ResponseEntity.ok(inscription);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteInscription(@PathVariable("id") Integer inscriptionId){
        inscriptionService.deleteInscription(inscriptionId);
        return ResponseEntity.ok("Inscription supprimée avec succès !");
    }
}
