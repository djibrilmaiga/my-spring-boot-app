package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.Instructeur;
import com.mycompany.fitmanager.web.entity.Seance;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.InstructeurRepository;
import com.mycompany.fitmanager.web.repository.SeanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SeanceService {

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private InstructeurRepository instructeurRepository;

    // POST
    public Seance createSeance(Seance newSeance){
        return seanceRepository.save(newSeance);
    }

    public Seance createSeanceSelectedCoach(Integer instructeurId, Seance newSeance){
        Instructeur instructeur = instructeurRepository.findById(instructeurId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructeur non trouvé à l'Id : " + instructeurId));

        newSeance.setInstructeur(instructeur);
        return seanceRepository.save(newSeance);
    }

    // GET ALL
    public List<Seance> getAllSeances(){
        return seanceRepository.findAll();
    }

    // GET ID
    public Seance getSeanceById(Integer id){
        return seanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seance introuvable à l'Id : " + id));
    }

    // PUT
    public Seance updateSeance(Integer id, Seance newSeance){
        Seance seance = seanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Seance introuvable à l'Id : " + id));

        seance.setTitre(newSeance.getTitre());
        seance.setTypeSeance(newSeance.getTypeSeance());
        seance.setNbreParticipants(newSeance.getNbreParticipants());
        seance.setDateHeure(newSeance.getDateHeure());
        seance.setDureeMinute(newSeance.getDureeMinute());
        seance.setDescription(newSeance.getDescription());
        seance.setStatut(newSeance.getStatut());


        return seanceRepository.save(seance);
    }

    // DELETE
    public void deleteSeance(Integer id){
        seanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seance introuvable à l'Id : " + id));
        seanceRepository.deleteById(id);
    }
}
