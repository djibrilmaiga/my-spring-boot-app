package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.dto.ExemplaireDTO;
import com.mycompany.fitmanager.web.entity.Exemplaire;
import com.mycompany.fitmanager.web.service.ExemplaireService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/exemplaire")
@CrossOrigin(origins = "http://localhost:3000")
public class ExemplaireController {

    @Autowired
    private ExemplaireService exemplaireService;

    // POST
    @PostMapping("/equipement/{id}")
    public ResponseEntity<Exemplaire> createExemplaire(@PathVariable("id") Integer equipementId,@RequestBody Exemplaire newExemplaire){
        Exemplaire exemplaire = exemplaireService.createExemplaire(equipementId, newExemplaire);
        return new ResponseEntity<>(exemplaire, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<ExemplaireDTO>> getAllExemplaire(){
        List<ExemplaireDTO> exemplaires = exemplaireService.getAllExemplaire();
        return ResponseEntity.ok(exemplaires);
    }

    // GET ID
    @GetMapping("{id}")
    public ResponseEntity<Exemplaire> getExemplaireById(@PathVariable("id") Integer exemplaireId){
        Exemplaire exemplaire = exemplaireService.getExemplaireById(exemplaireId);
        return ResponseEntity.ok(exemplaire);
    }

    // GET COUNT
    @GetMapping("/en-panne")
    public Integer getNombreExemplairesEnPanne() {
        return exemplaireService.getCountExemplaireEnPanne();
    }

    // PUT
    @PutMapping("{id}")
    public ResponseEntity<Exemplaire> updateExemplaire(@PathVariable("id") Integer exemplaireId,@RequestBody Exemplaire newExemplaire){
        Exemplaire exemplaire = exemplaireService.updateExemplaire(exemplaireId, newExemplaire);
        return ResponseEntity.ok(exemplaire);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteExemplaire(@PathVariable("id") Integer exemplaireId){
        exemplaireService.deleteExemplaire(exemplaireId);
        return ResponseEntity.ok("Exemplaire supprimé avec succès !");
    }
}
