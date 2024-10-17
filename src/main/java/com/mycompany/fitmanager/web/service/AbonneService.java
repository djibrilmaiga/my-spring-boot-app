package com.mycompany.fitmanager.web.service;


import com.mycompany.fitmanager.web.dto.AbonneSansAbonnementDTO;
import com.mycompany.fitmanager.web.dto.AbonneSelectedDTO;
import com.mycompany.fitmanager.web.entity.Abonne;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.AbonneRepository;
import com.mycompany.fitmanager.web.repository.PaiementRepository;
import com.mycompany.fitmanager.web.repository.TypeAbonnementRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AbonneService {

    @Autowired
    private AbonneRepository abonneRepository;

    @Autowired
    private TypeAbonnementRepository typeAbonnementRepository;

    @Autowired
    private PaiementRepository paiementRepository;

    // POST
    public Abonne createAbonne (Abonne abonne){
        return abonneRepository.save(abonne);
    }

    // GET ID
    public Abonne getAbonneById(Integer abonneId){
        return abonneRepository.findById(abonneId)
                .orElseThrow(() -> new ResourceNotFoundException("Abonné non trouvé à l'id : "+abonneId ));
    }

    // GET nombre total d'Abonnés
    public Integer getTotalAbonne(){
        return abonneRepository.totalAbonne();
    }

    // GET ALL
    public List<Abonne> getAllAbonnes(){
        List<Abonne> abonnes = abonneRepository.findAll();
        return abonnes;
    }

    // GET ALL tous les Abonnés sans abonnement actif
    public List<AbonneSansAbonnementDTO> getAbonnesSansAbonnementActif() {
        return abonneRepository.findAbonnesSansAbonnementActif();
    }

    // GET ALL tous les abonnés avec nom, prénom et téléphone
    public List<AbonneSelectedDTO> getAllAbonnesSelected(){
        return abonneRepository.findAbonneSelected();
    }

    // PUT
    public Abonne updateAbonne(Integer abonneId, Abonne updateAbonne){
        Abonne abonne = abonneRepository.findById(abonneId).orElseThrow(
                () -> new ResourceNotFoundException("Abonné non trouvé à l'id : "+ abonneId )
        );
        abonne.setNom(updateAbonne.getNom());
        abonne.setPrenom(updateAbonne.getPrenom());
        abonne.setGenre(updateAbonne.getGenre());
        abonne.setTelephone(updateAbonne.getTelephone());
        abonne.setEmail(updateAbonne.getEmail());
        abonne.setDateInscription(updateAbonne.getDateInscription());
        abonne.setPaiementTotal(updateAbonne.getPaiementTotal());

        return abonneRepository.save(abonne);
    }

    // DELETE
    public void deleteAbonne(Integer abonneId){
        Abonne abonne = abonneRepository.findById(abonneId).orElseThrow(
                () -> new ResourceNotFoundException("Abonné non trouvé à l'id : "+abonneId )
        );
        abonneRepository.deleteById(abonneId);
    }

}