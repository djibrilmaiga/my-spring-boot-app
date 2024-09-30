package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.entity.Instructeur;
import com.mycompany.fitmanager.web.service.InstructeurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/instructeur")
@CrossOrigin(origins = "http://localhost:3000")
public class InstructeurController {

    @Autowired
    private InstructeurService instructeurService;

    // POST
    @PostMapping
    public ResponseEntity<Instructeur> createInstructeur(@RequestBody Instructeur instructeur){
        Instructeur savedInstructeur = instructeurService.createInstructeur(instructeur);
        return new ResponseEntity<>(savedInstructeur, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Instructeur>> getAllInstructeurs(){
        List<Instructeur> instructeurs = instructeurService.getAllInstructeurs();
        return ResponseEntity.ok(instructeurs);
    }

    // GET
    @GetMapping("{id}")
    public ResponseEntity<Instructeur> getInstructeurById(@PathVariable("id") Integer instructeurId){
        Instructeur instructeur = instructeurService.getInstructeurById(instructeurId);
        return ResponseEntity.ok(instructeur);
    }

    // PUT
    @PutMapping("{id}")
    public ResponseEntity<Instructeur> updateInstructeur(@PathVariable("id") Integer instructeurId,@RequestBody Instructeur newInstructeur){
        Instructeur savedInstructeur = instructeurService.updateInstructeur(instructeurId, newInstructeur);
        return ResponseEntity.ok(savedInstructeur);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteInstructeur(@PathVariable("id") Integer instructeurId){
        instructeurService.deleteInstructeur(instructeurId);
        return ResponseEntity.ok("Instructeur supprimé avec succès !");
    }
}
