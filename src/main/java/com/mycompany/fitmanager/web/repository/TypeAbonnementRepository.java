package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.TypeAbonnement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository  extends JpaRepository<TypeAbonnement, Integer> {
}
