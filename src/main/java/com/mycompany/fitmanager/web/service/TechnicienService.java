package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnicienService {

    @Autowired
    private TechnicienRepository technicienRepository;
}
