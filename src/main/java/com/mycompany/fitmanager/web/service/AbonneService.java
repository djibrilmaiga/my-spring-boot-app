package com.mycompany.fitmanager.web.service;


import com.mycompany.fitmanager.web.dto.AbonneSansAbonnementDTO;
import com.mycompany.fitmanager.web.dto.AbonneSelectedDTO;
import com.mycompany.fitmanager.web.dto.AbonneSuscribeDTO;
import com.mycompany.fitmanager.web.entity.Abonne;
import com.mycompany.fitmanager.web.entity.Abonnement;
import com.mycompany.fitmanager.web.entity.Paiement;
import com.mycompany.fitmanager.web.entity.TypeAbonnement;
import com.mycompany.fitmanager.web.entity.enums.Statut;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.AbonneRepository;
import com.mycompany.fitmanager.web.repository.TypeAbonnementRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AbonneService {

    @Autowired
    private AbonneRepository abonneRepository;

    @Autowired
    private TypeAbonnementRepository typeAbonnementRepository;

    // POST
    public Abonne createAbonne (AbonneSuscribeDTO abonnedto){
        // Initiation de l'objet abonné
        Abonne abonne = new Abonne();
        // Conversion d'AbonneDTO en Abonne
        abonne.setNom(abonnedto.getNom());
        abonne.setPrenom(abonnedto.getPrenom());
        abonne.setGenre(abonnedto.getGenre());
        abonne.setTelephone(abonnedto.getTelephone());
        abonne.setEmail(abonnedto.getEmail());
        abonne.setDateInscription(abonnedto.getDateInscription());
        abonne.setPaiementTotal(abonnedto.getPaiementTotal());

        // Conversion de la liste des paiements
        List<Paiement> paiements = abonnedto.getPaiements().stream().map( dto -> {
            Paiement paiement = new Paiement();
            paiement.setTypePaiement(dto.getTypePaiement());
            paiement.setModePaiement(dto.getModePaiement());
            paiement.setDatePaiement(dto.getDatePaiement());
            paiement.setStatutPaiement(dto.getStatutPaiement());
            paiement.setMontantAPayer(dto.getMontantAPayer());
            paiement.setMontantPaye(dto.getMontantPaye());
            paiement.setMontantRestant(dto.getMontantRestant());
            paiement.setCommentaire(dto.getCommentaire());
            paiement.setAbonne(abonne); // Associer l'abonné
            return paiement;
        }).collect(Collectors.toList());

        // Conversion de la liste des abonnements
        List<Abonnement> abonnements = abonnedto.getAbonnements().stream().map(dto -> {
            Abonnement abonnement = new Abonnement();
            abonnement.setDateDebut(dto.getDateDebut());
            abonnement.setDateFin(dto.getDateFin());
            abonnement.setStatutAbonnement(Statut.valueOf(dto.getStatutAbonnement()));
            // Récupérer le TypeAbonnement en fonction de l'ID
            TypeAbonnement typeAbonnement = typeAbonnementRepository.findById(dto.getTypeId())
                    .orElseThrow(() -> new RuntimeException("Type d'abonnement introuvable"));
            abonnement.setType(typeAbonnement);
            abonnement.setAbonne(abonne); // Associer l'abonné
            return abonnement;
        }).collect(Collectors.toList());

        abonne.setPaiements(paiements);
        abonne.setAbonnements(abonnements);

        return abonneRepository.save(abonne);
    }

    // GET ID
    @Transactional
    public Abonne getAbonneById(Integer abonneId){
        return abonneRepository.findById(abonneId)
                .orElseThrow(() -> new ResourceNotFoundException("Abonné non trouvé à l'id : "+abonneId ));
    }

    // GET nombre total d'Abonnés
    @Transactional
    public Integer getTotalAbonne(){
        return abonneRepository.totalAbonne();
    }

    // GET ALL
    @Transactional
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