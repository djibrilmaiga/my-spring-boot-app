package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.dto.EquipementDTO;
import com.mycompany.fitmanager.web.entity.Equipement;
import com.mycompany.fitmanager.web.service.EquipementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/equipement")
@CrossOrigin(origins = "http://localhost:3000")
public class EquipementController {

    @Autowired
    private EquipementService equipementService;

    // POST
    @PostMapping("/categorie/{id}")
    public ResponseEntity<Equipement> createEquipement(@PathVariable("id") Integer categorieId,@RequestBody Equipement newEquipement){
        Equipement equipement = equipementService.createEquipement(categorieId, newEquipement);
        return new ResponseEntity<>(equipement, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<EquipementDTO>> getAllEquipements(){
        List<EquipementDTO> equipements = equipementService.getAllEquipement();
        return ResponseEntity.ok(equipements);
    }

    // GET ID
    @GetMapping("{id}")
    public ResponseEntity<Equipement> getEquipementByID(@PathVariable("id") Integer equipementId){
        Equipement equipement = equipementService.getEquipementById(equipementId);
        return ResponseEntity.ok(equipement);
    }

    // PUT
    @PutMapping("{id}")
    public ResponseEntity<Equipement> updateEquipement(@PathVariable("id") Integer equipementId,@RequestBody Equipement newEquipement){
        Equipement equipement = equipementService.updateEquipement(equipementId, newEquipement);
        return ResponseEntity.ok(equipement);
    }
    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEquipement(@PathVariable("id") Integer equipementId){
        equipementService.deleteEquipement(equipementId);
        return ResponseEntity.ok("Equipement supprimé avec succès !");
    }
}
