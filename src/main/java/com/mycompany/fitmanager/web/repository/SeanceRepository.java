package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Seance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeanceRepository extends CrudRepository<Seance, Integer> {
}