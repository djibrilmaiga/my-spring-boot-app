package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.Technicien;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.TechnicienRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TechnicienService {

    @Autowired
    private TechnicienRepository technicienRepository;

    // POST
    public Technicien createTechnicien(Technicien technicien){
        return technicienRepository.save(technicien);
    }

    // GET ALL
    public List<Technicien> getAllTechnicien(){
        return technicienRepository.findAll();
    }

    // GET
    public Technicien getTechnicienById(Integer id){
        Technicien technicien = technicienRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Techncien non trouvé à l'ID :" + id ));
        return technicien;
    }

    // PUT
    public Technicien updateTechnicien(Integer id, Technicien newTechnicien){
        Technicien technicien = technicienRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Techncien non trouvé à l'ID :" + id ));

        technicien.setNom(newTechnicien.getNom());
        technicien.setPrenom(newTechnicien.getPrenom());
        technicien.setTelephone(newTechnicien.getTelephone());

        return technicienRepository.save(technicien);
    }

    // DELETE
    public void deleteTechnicien(Integer id){
        technicienRepository.deleteById(id);
    }
}
