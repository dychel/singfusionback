package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.IntegrationMetierDTO;
import com.singfusion.singfusion.entity.*;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.IntegrationMetierRepository;
import com.singfusion.singfusion.repository.UserRepository;
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
    UserRepository userRepository;
    @Autowired
    QuizService quizService;
    @Autowired
    IntegrationMetierRepository integrationMetierRepository;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Override
    public IntegrationMetier saveIntegrationMetier(IntegrationMetierDTO integrationMetierDTO) {
        IntegrationMetier integrationMetier = modelMapper.map(integrationMetierDTO, IntegrationMetier.class);
        if (integrationMetierDTO.getUserId() != null){
            integrationMetier.setUsers(userService.getUserById(integrationMetierDTO.getUserId()));
        }
        currentdate = new Date(currentTimeInMillis);
        integrationMetier.setDateAjout(currentdate);
        integrationMetier.setTitre("Etapes Integration Metier, utilisateur"+ integrationMetierDTO.getUserId());
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
        integrationMetier.setDateAjout(currentdate);
        // MAJ id users
        //
        integrationMetier.setIsPowerPointRead(integrationMetierToUpdate.getIsPowerPointRead());
        integrationMetier.setIsPowerPointRead2(integrationMetierToUpdate.getIsPowerPointRead2());
        integrationMetier.setIsPowerPointRead3(integrationMetierToUpdate.getIsPowerPointRead3());
        integrationMetier.setIsPowerPointRead4(integrationMetierToUpdate.getIsPowerPointRead4());
        integrationMetier.setIsPowerPointRead5(integrationMetierToUpdate.getIsPowerPointRead5());
        integrationMetier.setIsPowerPointRead6(integrationMetierToUpdate.getIsPowerPointRead6());
        integrationMetier.setIsPowerPointRead7(integrationMetierToUpdate.getIsPowerPointRead7());

        integrationMetier.setIsVideoWatched(integrationMetierToUpdate.getIsVideoWatched());
        integrationMetier.setIsVideoWatched2(integrationMetierToUpdate.getIsVideoWatched2());
        integrationMetier.setIsVideoWatched3(integrationMetierToUpdate.getIsVideoWatched3());
        integrationMetier.setIsVideoWatched4(integrationMetierToUpdate.getIsVideoWatched4());

        //check powerpoint
        if (integrationMetierDTO.getIsPowerPointRead()!=null){
            integrationMetier.setIsPowerPointRead(integrationMetierDTO.getIsPowerPointRead());
        }
        if (integrationMetierDTO.getIsPowerPointRead2()!=null){
            integrationMetier.setIsPowerPointRead2(integrationMetierDTO.getIsPowerPointRead2());
        }
        if (integrationMetierDTO.getIsPowerPointRead3()!=null){
            integrationMetier.setIsPowerPointRead3(integrationMetierDTO.getIsPowerPointRead3());
        }
        if (integrationMetierDTO.getIsPowerPointRead4()!=null){
            integrationMetier.setIsPowerPointRead4(integrationMetierDTO.getIsPowerPointRead4());
        }
        if (integrationMetierDTO.getIsPowerPointRead5()!=null){
            integrationMetier.setIsPowerPointRead5(integrationMetierDTO.getIsPowerPointRead5());
        }
        if (integrationMetierDTO.getIsPowerPointRead4()!=null){
            integrationMetier.setIsPowerPointRead6(integrationMetierDTO.getIsPowerPointRead6());
        }
        if (integrationMetierDTO.getIsPowerPointRead7()!=null){
            integrationMetier.setIsPowerPointRead7(integrationMetierDTO.getIsPowerPointRead7());
        }
        //check videos
        if (integrationMetierDTO.getIsVideoWatched()!=null){
            integrationMetier.setIsVideoWatched(integrationMetierDTO.getIsVideoWatched());
        }
        if (integrationMetierDTO.getIsVideoWatched2()!=null){
            integrationMetier.setIsVideoWatched2(integrationMetierDTO.getIsVideoWatched2());
        }
        if (integrationMetierDTO.getIsVideoWatched3()!=null){
            integrationMetier.setIsVideoWatched3(integrationMetierDTO.getIsVideoWatched3());
        }
        if (integrationMetierDTO.getIsVideoWatched4()!=null){
            integrationMetier.setIsVideoWatched4(integrationMetierDTO.getIsVideoWatched4());
        }

        // on fait pareil pour
        if (integrationMetier.getIsVideoWatched() && integrationMetier.getIsVideoWatched2() && integrationMetier.getIsVideoWatched3() && integrationMetier.getIsVideoWatched4() && integrationMetier.getIsPowerPointRead() && integrationMetier.getIsPowerPointRead2() && integrationMetier.getIsPowerPointRead3() && integrationMetier.getIsPowerPointRead4()&& integrationMetier.getIsPowerPointRead5() && integrationMetier.getIsPowerPointRead6()&& integrationMetier.getIsPowerPointRead7()){
            integrationMetier.setIsFinished(true);
//            // si tout est okay on met a jour l'etat
//            Users users = userRepository.findByIdUser(integrationMetierDTO.getUserId());
//            users.setIsEtapes3Done(true);
            //userRepository.save(users);
        }
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
