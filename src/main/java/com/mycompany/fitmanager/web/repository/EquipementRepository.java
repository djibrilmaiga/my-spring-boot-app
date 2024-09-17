package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Equipement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipementRepository extends CrudRepository<Equipement,Integer> {
}
