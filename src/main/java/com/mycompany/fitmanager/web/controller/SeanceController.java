package com.mycompany.fitmanager.web.controller;

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
    @PostMapping
    public ResponseEntity<Seance> createSeance(@RequestBody Seance seance){
        Seance savedSeance = seanceService.createSeance(seance);
        return new ResponseEntity<>(savedSeance, HttpStatus.CREATED);
    }

    @PostMapping("/instructeur/{instructeurId}")
    public ResponseEntity<Seance> createSeancewithCoach(@PathVariable("instructeurId") Integer instructeurId,@RequestBody Seance seance){
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
    // PUT
    @PutMapping("{id}")
    public ResponseEntity<Seance> updateSeance(@PathVariable("id") Integer seanceId, @RequestBody Seance newSeance){
        Seance seance = seanceService.updateSeance(seanceId, newSeance);
        return ResponseEntity.ok(seance);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSeance(@PathVariable Integer seanceId){
        seanceService.deleteSeance(seanceId);
        return ResponseEntity.ok("Seance supprimée avec succès !");
    }
}
