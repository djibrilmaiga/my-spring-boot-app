package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.entity.Utilisateur;
import com.mycompany.fitmanager.web.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/utilisateur")
@CrossOrigin(origins = "http://localhost:3000")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // POST
    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        if (utilisateurService.existsByLogin(utilisateur.getLogin())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Utilisateur createdUtilisateur = utilisateurService.create(utilisateur);
        return new ResponseEntity<>(createdUtilisateur, HttpStatus.CREATED);
    }
    // GET ALL
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateur(){
        List<Utilisateur> utilisateurs = utilisateurService.findAll();
        return ResponseEntity.ok(utilisateurs);
    }

    // GET ID
    @GetMapping("{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable("id") Integer id) {
        Utilisateur utilisateur = utilisateurService.findById(id);
        return ResponseEntity.ok(utilisateur);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable("id") Integer id, @RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur updatedUtilisateur = utilisateurService.update(id, utilisateur);
            return ResponseEntity.ok(updatedUtilisateur);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable("id") Integer id) {
        utilisateurService.deleteById(id);
        return ResponseEntity.ok("Utilisateur supprimé avec succès !");
    }
}
