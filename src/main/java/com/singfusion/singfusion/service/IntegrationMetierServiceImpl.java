package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.IntegrationMetierDTO;
import com.singfusion.singfusion.entity.*;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.IntegrationMetierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IntegrationMetierServiceImpl implements IntegrationMetierService{
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;
    @Autowired
    ContenusService contenusService;
    @Autowired
    QuizService quizService;
    IntegrationMetierRepository integrationMetierRepository;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Override
    public IntegrationMetier saveIntegrationMetier(IntegrationMetierDTO integrationMetierDTO) {
        IntegrationMetier integrationMetier = modelMapper.map(integrationMetierDTO, IntegrationMetier.class);
        return integrationMetierRepository.save(integrationMetier);
    }

    @Override
    public IntegrationMetier updateIntegrationMetier(Long id, IntegrationMetierDTO integrationMetierDTO) {
        IntegrationMetier integrationMetierToUpdate = integrationMetierRepository.findByIdIntegrationMetier(id);
        currentdate = new Date(currentTimeInMillis);
        if (integrationMetierToUpdate == null)
            throw new ApiRequestException("Integration Metier ID non trouvé");
        IntegrationMetier integrationMetier = modelMapper.map(integrationMetierDTO, IntegrationMetier.class);
        integrationMetier.setId(integrationMetierToUpdate.getId());
        integrationMetier.setDateajout(currentdate);
        // MAJ id users
        updateForeignKeyUsersContenusQuiz(integrationMetierDTO, integrationMetier);
        return integrationMetierRepository.save(integrationMetier);
    }

    private void updateForeignKeyUsersContenusQuiz(IntegrationMetierDTO integrationMetierDTO, IntegrationMetier integrationMetier) {
        // mettre a jour id contenus si pas null
        if (integrationMetierDTO.getContenuId() != null )
            integrationMetier.setContenus(contenusService.findContenusById(integrationMetierDTO.getContenuId()));
        // mettre a jour id users si pas null
        if (integrationMetierDTO.getUserId() != null)
            integrationMetier.setUsers(userService.getUserById(integrationMetierDTO.getUserId()));
        // mettre a jour id quiz si pas null
        if (integrationMetierDTO.getQuizId() != null)
            integrationMetier.setQuiz(quizService.findQuizById(integrationMetierDTO.getQuizId()));
    }
    @Override
    public IntegrationMetier findIntegrationMetierById(Long id) {
        return integrationMetierRepository.findByIdIntegrationMetier(id);
    }

    @Override
    public IntegrationMetier findIntegrationMetierByIdUsers(Long id) {
        Users users = userService.getUserById(id);
        if (users == null)
            throw new ApiRequestException("User non trouvé");
        return integrationMetierRepository.findIntegrationMetierByIdUsers(id);
    }

    @Override
    public IntegrationMetier findIntegrationMetierByIdQuiz(Long id) {
        Quiz quiz = quizService.findQuizById(id);
        if (quiz == null)
            throw new ApiRequestException("Quiz non trouvé");
        return integrationMetierRepository.findIntegrationMetierByIdQuiz(id);
    }

    @Override
    public IntegrationMetier findIntegrationMetierByIdContenus(Long id) {
        Contenus contenus = contenusService.findContenusById(id);
        if (contenus == null)
            throw new ApiRequestException("Contenu non trouvé");
        return integrationMetierRepository.findIntegrationMetierByIdContenus(id);
    }

    @Override
    public List<IntegrationMetier> listIntegrationMetier() {
        return integrationMetierRepository.findAll();
    }

    @Override
    public void deleteIntegrationMetierById(Long id) {
        IntegrationMetier integrationMetier = integrationMetierRepository.findByIdIntegrationMetier(id);
        if (integrationMetier==null)
            throw new ApiRequestException("ID Integration Metier non trouve");
        integrationMetierRepository.deleteById(id);
    }
}
