package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.entity.Technicien;
import com.mycompany.fitmanager.web.service.TechnicienService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/technicien")
@CrossOrigin(origins = "http://localhost:3000")
public class TechnicienController {

    @Autowired
    private TechnicienService technicienService;

    // POST
    @PostMapping
    public ResponseEntity<Technicien> createTechnicien(@RequestBody Technicien technicien){
        Technicien savedTechnicen = technicienService.save(technicien);
        return new ResponseEntity<>(savedTechnicen, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Technicien>> getAllTechniciens(){
        List<Technicien> techniciens = technicienService.findAll();
        return ResponseEntity.ok(techniciens);
    }

    // GET
    @GetMapping("{id}")
    public ResponseEntity<Technicien> getTechnicienById(@PathVariable("id") Integer technicienId){
        return technicienService.findById(technicienId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT
    @PutMapping("{id}")
    public ResponseEntity<Technicien> updateTechnicien(@PathVariable("id") Integer technicienId, @RequestBody Technicien newTechnicien){
        Technicien technicien = technicienService.updateTechnicien(technicienId, newTechnicien);
        return ResponseEntity.ok(technicien);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTechnicien(@PathVariable("id") Integer technicienId){
        technicienService.deleteById(technicienId);
        return ResponseEntity.ok("Technicien supprimé avec succès !");
    }
}
