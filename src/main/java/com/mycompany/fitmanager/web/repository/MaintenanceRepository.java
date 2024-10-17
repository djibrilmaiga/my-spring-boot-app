package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance,Integer> {
    List<Maintenance> findByTechnicienId(Integer technicienId);

    @Query("SELECT m FROM Maintenance m JOIN FETCH m.technicien t JOIN FETCH m.exemplaire e JOIN FETCH e.equipement eq")
    List<Maintenance> findAllMaintenances();
}
