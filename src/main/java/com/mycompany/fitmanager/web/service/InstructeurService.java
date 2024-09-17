package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.repository.InstructeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructeurService {

    @Autowired
    private InstructeurRepository instructeurRepository;
}
