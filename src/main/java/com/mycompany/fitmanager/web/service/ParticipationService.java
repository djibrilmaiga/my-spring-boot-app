package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipationService {

    @Autowired
    private ParticipationRepository participationRepository;
}