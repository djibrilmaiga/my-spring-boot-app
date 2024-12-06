package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.Technicien;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.TechnicienRepository;
import org.springframework.stereotype.Service;

@Service
public class TechnicienService extends AgentService<Technicien> {

    private final TechnicienRepository technicienRepository;

    public TechnicienService(TechnicienRepository technicienRepository) {
        this.technicienRepository = technicienRepository;
    }

    // POST
    @Override
    public Technicien save(Technicien technicien) {
        return technicienRepository.save(technicien);
    }

    // PUT
    public Technicien updateTechnicien(Integer id, Technicien newTechnicien) {
        Technicien existingTechnicien = findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Technicien non trouvé à l'ID : " + id)
        );

        existingTechnicien.setNom(newTechnicien.getNom());
        existingTechnicien.setPrenom(newTechnicien.getPrenom());
        existingTechnicien.setTelephone(newTechnicien.getTelephone());

        return save(existingTechnicien);
    }

}
