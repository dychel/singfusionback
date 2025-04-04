package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.IntegrationMetierDTO;
import com.singfusion.singfusion.entity.IntegrationMetier;
import java.util.List;

public interface IntegrationMetierService {

    IntegrationMetier saveIntegrationMetier(IntegrationMetierDTO integrationMetierDTO);
    IntegrationMetier updateIntegrationMetier(Long id, IntegrationMetierDTO integrationMetierDTO);
    IntegrationMetier findIntegrationMetierById(Long id);
    IntegrationMetier findIntegrationMetierByIdUsers(Long id);
    IntegrationMetier findIntegrationMetierByIdQuiz(Long id);
    IntegrationMetier findIntegrationMetierByIdContenus(Long id);
    List<IntegrationMetier> listIntegrationMetier();
    void deleteIntegrationMetierById(Long id);
}
