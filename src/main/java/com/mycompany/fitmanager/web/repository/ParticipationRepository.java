package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Participation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends CrudRepository<Participation, Integer> {
}