package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.dto.AbonneDTO;
import com.mycompany.fitmanager.web.entity.Participation;
import com.mycompany.fitmanager.web.entity.enums.StatutPresence;
import com.mycompany.fitmanager.web.service.ParticipationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/participation")
@CrossOrigin(origins = "http://localhost:3000")
public class ParticipationController {

    @Autowired
    private ParticipationService participationService;

    // POST
    @PostMapping("/cours/{coursId}/abonne/{abonneId}")
    public ResponseEntity<Participation> createParticipation(
            @PathVariable("coursId") Integer seanceId,
            @PathVariable("abonneId") Integer abonneId,
            @RequestParam StatutPresence presence){
        return ResponseEntity.ok(participationService.createParticipation(seanceId, abonneId, presence));
    }

    // GET ALL
    @GetMapping("/seance/{seanceId}")
    public ResponseEntity<List<AbonneDTO>> getAllAbonneInCourse(@PathVariable("seanceId") Integer seanceId){
        List<AbonneDTO> abonneDTO = participationService.getAllAbonneInCourse(seanceId);
        return ResponseEntity.ok(abonneDTO);
    }
    // GET ID
    @GetMapping("/taux-participation")
    public double obtenirTauxParticipation() {
        return participationService.calculerTauxParticipation();
    }

    // PUT
    @PostMapping("{id}")
    public ResponseEntity<Participation> updateParticipation(@PathVariable("id") Integer id, @RequestParam StatutPresence presence){
        return ResponseEntity.ok(participationService.updateParticipation(id, presence));
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteParticipation(@PathVariable("id") Integer id){
        participationService.deleteParticipation(id);
        return ResponseEntity.ok("Participation supprimé avec succès !");
    }
}
