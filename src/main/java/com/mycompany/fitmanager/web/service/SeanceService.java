package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.repository.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeanceService {

    @Autowired
    private SeanceRepository seanceRepository;
}
