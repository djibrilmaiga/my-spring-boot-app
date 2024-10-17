package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.dto.CoachDTO;
import com.mycompany.fitmanager.web.entity.Seance;
import com.mycompany.fitmanager.web.service.SeanceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/seance")
@CrossOrigin(origins = "http://localhost:3000")
public class SeanceController {

    @Autowired
    private SeanceService seanceService;

    // POST
    @PostMapping("/instructeur/{instructeurId}")
    public ResponseEntity<Seance> createSeancewithCoach(@PathVariable("instructeurId") Integer instructeurId, @RequestBody Seance seance){
        Seance savedSeance = seanceService.createSeanceSelectedCoach(instructeurId, seance);
        return new ResponseEntity<>(savedSeance, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Seance>> getAllSeances(){
        List<Seance> seances = seanceService.getAllSeances();
        return ResponseEntity.ok(seances);
    }

    // GET ID
    @GetMapping("{id}")
    public ResponseEntity<Seance> getSeanceById(@PathVariable("id") Integer seanceId){
        Seance seance = seanceService.getSeanceById(seanceId);
        return ResponseEntity.ok(seance);
    }

    @GetMapping("/{seanceId}/instructeur")
    public ResponseEntity<CoachDTO> getInstructeurBySeanceId(@PathVariable Integer seanceId) {
        CoachDTO instructeurDTO = seanceService.getInstructeurBySeanceId(seanceId);
        if (instructeurDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(instructeurDTO);
    }

    // PUT
    @PutMapping("{id}")
    public ResponseEntity<Seance> updateSeance(@PathVariable("id") Integer seanceId, @RequestBody Seance newSeance){
        Seance seance = seanceService.updateSeance(seanceId, newSeance);
        return ResponseEntity.ok(seance);
    }

    // PUT Inscrire des abonnés à un cours
    @PutMapping("/inscrire/{id}")
    public ResponseEntity<Seance> inscrireSeance(@PathVariable("id") Integer seanceId, @RequestBody Seance newSeance){
        Seance seance = seanceService.inscrireCours(seanceId, newSeance);
        return ResponseEntity.ok(seance);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSeance(@PathVariable Integer seanceId){
        seanceService.deleteSeance(seanceId);
        return ResponseEntity.ok("Seance supprimée avec succès !");
    }
}
