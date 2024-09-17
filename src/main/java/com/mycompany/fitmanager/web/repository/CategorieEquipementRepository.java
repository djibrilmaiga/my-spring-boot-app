package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.TypeEquipement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieEquipementRepository extends CrudRepository<TypeEquipement, Integer> {
}
