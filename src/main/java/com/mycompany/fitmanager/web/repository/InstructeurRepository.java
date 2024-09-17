package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Instructeur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructeurRepository extends CrudRepository<Instructeur, Integer> {
}
