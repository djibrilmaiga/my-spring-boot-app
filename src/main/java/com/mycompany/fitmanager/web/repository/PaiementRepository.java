package com.mycompany.fitmanager.web.repository;

import com.mycompany.fitmanager.web.dto.PaiementAbonneDTO;
import com.mycompany.fitmanager.web.dto.PaiementParModeDTO;
import com.mycompany.fitmanager.web.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Integer> {
    // Trouver tous les paiements liés à un abonné
    List<Paiement> findByAbonneId(Integer abonneId);

    @Query("SELECT new com.mycompany.fitmanager.web.dto.PaiementAbonneDTO(p.id, p.typePaiement, p.modePaiement, p.datePaiement, p.statutPaiement, p.montantAPayer, p.montantPaye, p.montantRestant, p.commentaire, a.id, a.nom, a.prenom) " +
            "FROM Paiement p JOIN p.abonne a")
    List<PaiementAbonneDTO> findAllPaiementsWithAbonneInfo();

    @Query("SELECT SUM(p.montantPaye) FROM Paiement p WHERE YEAR(p.datePaiement) = :year AND MONTH(p.datePaiement) = :month")
    BigDecimal sumPaymentsByMonth(int year, int month);

    @Query("SELECT new com.mycompany.fitmanager.web.dto.PaiementParModeDTO(p.modePaiement, SUM(p.montantPaye)) " +
            "FROM Paiement p GROUP BY p.modePaiement")
    List<PaiementParModeDTO> findSumPaymentsByModePaiement();
}