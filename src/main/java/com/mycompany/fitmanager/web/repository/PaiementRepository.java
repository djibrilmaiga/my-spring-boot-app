package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Integer> {
}
