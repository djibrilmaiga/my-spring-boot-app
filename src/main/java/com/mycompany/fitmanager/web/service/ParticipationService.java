package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.dto.AbonneDTO;
import com.mycompany.fitmanager.web.entity.Abonne;
import com.mycompany.fitmanager.web.entity.Participation;
import com.mycompany.fitmanager.web.entity.Seance;
import com.mycompany.fitmanager.web.entity.enums.StatutPresence;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.AbonneRepository;
import com.mycompany.fitmanager.web.repository.ParticipationRepository;
import com.mycompany.fitmanager.web.repository.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipationService {

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private AbonneRepository abonneRepository;

    // POST
    public Participation createParticipation (Integer seanceId, Integer abonneId, StatutPresence presence){
        Optional<Seance> seance = seanceRepository.findById(seanceId);
        Optional<Abonne> abonne = abonneRepository.findById(abonneId);

        if(seance.isPresent() && abonne.isPresent()){
            Participation participation = new Participation();
            participation.setSeance(seance.get());
            participation.setAbonne(abonne.get());
            participation.setStatut(presence);
            return participationRepository.save(participation);
        } else {
            throw new ResourceNotFoundException("Seance ou Abonné introuvable !");
        }
    }

    // GET ALL
    public List<AbonneDTO> getAllAbonneInCourse(Integer seanceId){
        return participationRepository.findBySeanceId(seanceId);
    }

    // GET ID

    // PUT
    public Participation updateParticipation (Integer participationId, StatutPresence presence){
        Participation participation = participationRepository.findById(participationId)
                .orElseThrow(() -> new ResourceNotFoundException("Participation introuvable à l'Id : " + participationId));

        participation.setStatut(presence);
        return participationRepository.save(participation);
    }

    // DELETE
    public void deleteParticipation(Integer participationId){
        participationRepository.findById(participationId)
                .orElseThrow(() -> new ResourceNotFoundException("Participation introuvable à l'Id : " + participationId));
        participationRepository.deleteById(participationId);
    }
}