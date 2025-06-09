package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.RapportEtonnementDTO;
import com.singfusion.singfusion.entity.RapportEtonnement;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.List;

public interface RapportEtonnementService {

    @Transactional
    RapportEtonnement saveRapportEtonnement(RapportEtonnementDTO rapportEtonnementDTO) throws IOException;
    RapportEtonnement updateRapportEtonnement(Long id, RapportEtonnementDTO rapportEtonnementDTO);
    RapportEtonnement findRapportEtonnementById(Long id);
    RapportEtonnement findRapportEtonnementByIdUsers(Long id);
    RapportEtonnement findRapportEtonnementByIdQuiz(Long id);
    RapportEtonnement findRapportEtonnementByIdDocument(Long id);
    List<RapportEtonnement> listRapportEtonnement();
    void deleteRapportEtonnementById(Long id);
}
