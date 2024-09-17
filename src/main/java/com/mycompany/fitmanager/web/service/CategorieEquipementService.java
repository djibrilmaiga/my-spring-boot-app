package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.repository.CategorieEquipementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategorieEquipementService {

    @Autowired
    private CategorieEquipementRepository categorieEquipementRepository;
}
