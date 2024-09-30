package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Instructeur;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InstructeurRepository extends JpaRepository<Instructeur, Integer> {
}