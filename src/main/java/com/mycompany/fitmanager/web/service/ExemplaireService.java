package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.repository.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExemplaireService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;
}
