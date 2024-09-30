package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.entity.Categorie;
import com.mycompany.fitmanager.web.service.CategorieEquipementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/categorie")
@CrossOrigin(origins = "http://localhost:3000")
public class CategorieController {

    @Autowired
    private CategorieEquipementService categorieService;

    // POST
    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie newCategorie){
        Categorie categorie = categorieService.createCategorie(newCategorie);
        return new ResponseEntity<>(categorie, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Categorie>> getAllCategories(){
        List<Categorie> categories = categorieService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // GET ID
    @GetMapping("{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable("id") Integer categorieId){
        Categorie categorie = categorieService.getCategorieById(categorieId);
        return ResponseEntity.ok(categorie);
    }

    // PUT
    @PutMapping("{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable("id") Integer id, @RequestBody Categorie newCategorie){
        if (newCategorie.getLibelle() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Categorie categorie = categorieService.updateCategorie(id, newCategorie);
        return ResponseEntity.ok(categorie);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategorie(@PathVariable("id") Integer id){
        categorieService.deleteCategorie(id);
        return ResponseEntity.ok("Categorie supprimée avec succès !");
    }
}
