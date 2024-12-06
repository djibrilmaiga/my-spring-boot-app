package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AgentRepository<T extends Agent> extends JpaRepository<T, Integer> {
}
