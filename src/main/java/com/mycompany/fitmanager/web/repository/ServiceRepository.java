package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository  extends JpaRepository<Service, Integer> {
}
