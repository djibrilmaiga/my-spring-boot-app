package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.repository.EquipementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipementService {

    @Autowired
    private EquipementRepository equipementRepository;
}
