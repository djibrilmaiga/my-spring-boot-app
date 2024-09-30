package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.dto.TypeAbonnementDTO;
import com.mycompany.fitmanager.web.entity.TypeAbonnement;
import com.mycompany.fitmanager.web.service.TypeAbonnementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/typeabonnement")
@CrossOrigin(origins = "http://localhost:3000")
public class TypeAbonnementController {

    private TypeAbonnementService typeAbonnementService;

    // POST
    @PostMapping
    public ResponseEntity<TypeAbonnement> createType(@RequestBody TypeAbonnement newTypeAbonnement){
        TypeAbonnement typeAbonnement = typeAbonnementService.createTypeAbonnement(newTypeAbonnement);
        return new ResponseEntity<>(typeAbonnement, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<TypeAbonnementDTO>> getAllType(){
        List<TypeAbonnementDTO> typeAbonnements = typeAbonnementService.getAllTypeAbonnement();
        return ResponseEntity.ok(typeAbonnements);
    }

    // GET ID
    @GetMapping("{id}")
    public ResponseEntity<TypeAbonnementDTO> getTypeById(@PathVariable("id") Integer typeId){
        TypeAbonnementDTO typeAbonnement = typeAbonnementService.getTypeAbonnementById(typeId);
        return ResponseEntity.ok(typeAbonnement);
    }

    // PUT
    @PutMapping("{id}")
    public ResponseEntity<TypeAbonnement> updateType(@PathVariable("id") Integer typeId,@RequestBody TypeAbonnement newTypeAbonnement){
        TypeAbonnement typeAbonnement = typeAbonnementService.updateTypeAbonnement(typeId, newTypeAbonnement);
        return ResponseEntity.ok(typeAbonnement);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteType(@PathVariable("id") Integer typeId){
        typeAbonnementService.deleteTypeAbonnement(typeId);
        return ResponseEntity.ok("Type Abonnement supprimé avec succès !");
    }
}