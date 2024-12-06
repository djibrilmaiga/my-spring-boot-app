package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.entity.Utilisateur;
import com.mycompany.fitmanager.web.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/utilisateur")
@RequiredArgsConstructor
public class UtilisateurController {
    // Propriétés
    private final UtilisateurService utilisateurService;

    // ---Méthodes REST
    // POST
    @PostMapping
    public ResponseEntity<Utilisateur> createUser(@RequestBody Utilisateur utilisateur){
        Utilisateur newUser = utilisateurService.createUser(utilisateur);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUsers(){
        List<Utilisateur> users = utilisateurService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // GET ID
    @GetMapping("{id}")
    public ResponseEntity<Utilisateur> getUserById(@PathVariable("id") Integer userId){
        Utilisateur user = utilisateurService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    // PUT
    @PutMapping("{id}")
    public ResponseEntity<Utilisateur> updateUser(@PathVariable("id") Integer userId,
                                                  @RequestBody Utilisateur newUser){
        Utilisateur user = utilisateurService.updateUser(userId, newUser);
        return ResponseEntity.ok(user);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer userId){
        utilisateurService.deleteUserById(userId);
        return ResponseEntity.ok("Utilisateur supprimé avec succès !");
    }
}