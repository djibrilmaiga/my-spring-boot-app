package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Technicien;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicienRepository extends CrudRepository<Technicien,Integer> {
}
