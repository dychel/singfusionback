package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.RapportEtonnementDTO;
import com.singfusion.singfusion.entity.RapportEtonnement;
import java.util.List;

public interface RapportEtonnementService {

    RapportEtonnement saveRapportEtonnement(RapportEtonnementDTO rapportEtonnementDTO);
    RapportEtonnement updateRapportEtonnement(Long id, RapportEtonnementDTO rapportEtonnementDTO);
    RapportEtonnement findRapportEtonnementById(Long id);
    RapportEtonnement findRapportEtonnementByIdUsers(Long id);
    RapportEtonnement findRapportEtonnementByIdQuiz(Long id);
    RapportEtonnement findRapportEtonnementByIdDocument(Long id);
    List<RapportEtonnement> listRapportEtonnement();
    void deleteRapportEtonnementById(Long id);
}
