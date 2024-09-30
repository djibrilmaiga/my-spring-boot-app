package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.dto.PaiementAbonneDTO;
import com.mycompany.fitmanager.web.dto.PaiementParModeDTO;
import com.mycompany.fitmanager.web.entity.Abonne;
import com.mycompany.fitmanager.web.entity.Paiement;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.AbonneRepository;
import com.mycompany.fitmanager.web.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private AbonneRepository abonneRepository;

    // POST
    public Paiement createAbonne(Integer abonneId, Paiement newPaiement) {
        Abonne abonne = abonneRepository.findById(abonneId)
                .orElseThrow(() -> new ResourceNotFoundException("Abonné non trouvé à l'Id : " + abonneId));

        // Mise à jour du paiement total
        abonne.setPaiementTotal(abonne.getPaiementTotal().add(newPaiement.getMontantPaye()));
        newPaiement.setAbonne(abonne);

        abonneRepository.save(abonne);
        return paiementRepository.save(newPaiement);
    }

    // GET
    public Paiement getPaiementById(Integer paiementId){
        Paiement paiement = paiementRepository.findById(paiementId)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement non trouvé à l'id : " + paiementId));
        return paiement;
    }

    public BigDecimal getSommePaiementsByMonth(Integer year, Integer month){
        return paiementRepository.sumPaymentsByMonth(year, month);
    }

    public List<PaiementParModeDTO> getTotalPaiementsByModePaiement(){
        return paiementRepository.findSumPaymentsByModePaiement();
    }

    // GET ALL
    public List<Paiement> getAllPaiement(){
        List<Paiement> paiements = paiementRepository.findAll();
        return paiements.stream().collect(Collectors.toList());
    }

    public List<PaiementAbonneDTO> getAllPaiementWithAbonneInfo(){
        return paiementRepository.findAllPaiementsWithAbonneInfo();
    }

    // PUT
    public Paiement updatePaiement(Integer paiementId, Paiement updatedPaiement) {
        Paiement existingPaiement = paiementRepository.findById(paiementId)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement non trouvé à l'id : " + paiementId));

        Abonne abonne = existingPaiement.getAbonne();

        // Mise à jour du paiement total (soustraction de l'ancien paiement et ajout du nouveau)
        BigDecimal montantAncienPaiement = existingPaiement.getMontantPaye();
        BigDecimal montantNouveauPaiement = updatedPaiement.getMontantPaye();
        abonne.setPaiementTotal(abonne.getPaiementTotal().subtract(montantAncienPaiement).add(montantNouveauPaiement));

        // Mise à jour des informations du paiement
        existingPaiement.setTypePaiement(updatedPaiement.getTypePaiement());
        existingPaiement.setModePaiement(updatedPaiement.getModePaiement());
        existingPaiement.setDatePaiement(updatedPaiement.getDatePaiement());
        existingPaiement.setStatutPaiement(updatedPaiement.getStatutPaiement());
        existingPaiement.setMontantAPayer(updatedPaiement.getMontantAPayer());
        existingPaiement.setMontantPaye(montantNouveauPaiement);
        existingPaiement.setMontantRestant(updatedPaiement.getMontantRestant());
        existingPaiement.setCommentaire(updatedPaiement.getCommentaire());

        abonneRepository.save(abonne);
        return paiementRepository.save(existingPaiement);
    }

    // DELETE
    public void deletePaiement(Integer paiementId) {
        Paiement paiement = paiementRepository.findById(paiementId)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement non trouvé à l'id : " + paiementId));

        Abonne abonne = paiement.getAbonne();

        // Mise à jour du paiement total (soustraction du paiement supprimé)
        abonne.setPaiementTotal(abonne.getPaiementTotal().subtract(paiement.getMontantPaye()));

        abonneRepository.save(abonne);
        paiementRepository.deleteById(paiementId);
    }

}