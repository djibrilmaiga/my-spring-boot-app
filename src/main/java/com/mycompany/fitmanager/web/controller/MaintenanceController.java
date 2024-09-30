package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.dto.MaintenanceDTO;
import com.mycompany.fitmanager.web.entity.Maintenance;
import com.mycompany.fitmanager.web.service.MaintenanceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin(origins = "http://localhost:3000")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    // POST
    @PostMapping("/exemplaire/{exemplaireId}/technicien/{technicienId}")
    public ResponseEntity<Maintenance> createMaintenance(
            @PathVariable("exemplaireId") Integer exemplaireId,
            @PathVariable("technicienId") Integer technicienId,
            @RequestBody Maintenance maintenance){
        Maintenance savedMaintenance = maintenanceService.createMaintenance(exemplaireId,technicienId, maintenance);
        return new ResponseEntity<>(maintenance, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<MaintenanceDTO>> getAllMaintenances() {
        List<MaintenanceDTO> maintenances = maintenanceService.getAllMaintenances();
        return ResponseEntity.ok(maintenances);
    }

    // GET ID
    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> getMaintenanceById(@PathVariable("id") Integer id) {
        MaintenanceDTO maintenance = maintenanceService.getMaintenanceById(id);
        return maintenance != null ? ResponseEntity.ok(maintenance) : ResponseEntity.notFound().build();
    }
    // PUT
    @PutMapping("{id}")
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable("id") Integer maintenanceId,@RequestBody Maintenance newMaintenance){
        Maintenance maintenance = maintenanceService.updateMaintenance(maintenanceId, newMaintenance);
        return ResponseEntity.ok(maintenance);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMaintenance(@PathVariable("id") Integer maintenanceId){
        maintenanceService.deleteMaintenance(maintenanceId);
        return ResponseEntity.ok("Maintenance supprimée avec succès !");
    }
}
