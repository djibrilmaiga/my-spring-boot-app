package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AgentService<T extends Agent> {

    @Autowired
    private JpaRepository<T, Integer> repository;

    public List<T> findAll() {
        return repository.findAll();
    }

    public Optional<T> findById(Integer id) {
        return repository.findById(id);
    }

    public T save(T agent) {
        return repository.save(agent);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
