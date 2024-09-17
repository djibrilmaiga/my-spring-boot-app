package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Exemplaire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaireRepository extends CrudRepository<Exemplaire, String> {
}
