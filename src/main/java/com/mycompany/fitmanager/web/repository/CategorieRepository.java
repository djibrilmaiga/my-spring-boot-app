package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Categorie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieEquipementRepository extends CrudRepository<Categorie, Integer> {
}
