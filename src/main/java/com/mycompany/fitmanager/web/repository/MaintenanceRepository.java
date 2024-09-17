package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Maintenance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends CrudRepository<Maintenance,Integer> {
}
