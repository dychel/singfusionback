package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.QuizResultDTO;
import com.singfusion.singfusion.entity.*;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.ConnaissanceDonneeRepository;
import com.singfusion.singfusion.repository.QuizResultRepository;
import com.singfusion.singfusion.repository.RapportEtonnementRepository;
import com.singfusion.singfusion.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class QuizResultServiceImpl implements QuizResultService{

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;
    @Autowired
    QuizService quizService;

    @Autowired
    QuestionsService questionsService;
    @Autowired
    QuizResultRepository quizResultRepository;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Autowired
    ConnaissanceDonneeService connaissanceDonneeService;
    @Autowired
    ConnaissanceDonneeRepository connaissanceDonneeRepository;
    @Autowired
    RapportEtonnementService rapportEtonnementService;

    @Autowired
    RapportEtonnementRepository rapportEtonnementRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public QuizResult saveQuizResult(QuizResultDTO quizResultDTO) {
        QuizResult quizResult = modelMapper.map(quizResultDTO, QuizResult.class);

        //on met a jour le user
        //recuperer le total question

        Quiz quiz = quizService.findQuizById(quizResultDTO.getQuizId());
        quizResult.setTotalQuestion((long) quiz.getQuestions().size());
        if (quizResultDTO.getUserId() != null)
            quizResult.setUsers(userService.getUserById(quizResultDTO.getUserId()));
        // si le quiz est reussi, on passe a l'etape suivante. Pour présentation
        QuizResult quizResultTwo = quizResultRepository.findQuizResultByTitre("PRESENTATION", quizResultDTO.getUserId());
        if(quizResultTwo!=null && quizResultTwo.getIsUserSucceed()){
//          si tout est okay on met a jour l'etat
            Users users = userRepository.findByIdUser(quizResultDTO.getUserId());
            users.setIsEtapes2Done(true);
            userRepository.save(users);
        }

        // si le quiz est reussi, on passe a l'etape suivante. Pour Integration
        QuizResult quizResultIntegration = quizResultRepository.findQuizResultByTitre("INTEGRATIONMETIER", quizResultDTO.getUserId());
        if(quizResultIntegration!=null && quizResultIntegration.getIsUserSucceed()){
//          si tout est okay on met a jour l'etat
            Users users = userRepository.findByIdUser(quizResultDTO.getUserId());
            users.setIsEtapes3Done(true);
            userRepository.save(users);
        }

        // si le quiz est reussi, on passe a l'etape suivante. Pour Connaissance
        QuizResult quizResulTKce = quizResultRepository.findQuizResultByTitre("CONNAISSANCE", quizResultDTO.getUserId());
        if(quizResulTKce!=null && quizResulTKce.getIsUserSucceed()){
//          si tout est okay on met a jour l'etat
            Users users = userRepository.findByIdUser(quizResultDTO.getUserId());
            users.setIsEtapes4done(true);
            //mettre a jour connaisance
            ConnaissanceDonnee connaissanceDonnee=connaissanceDonneeService.findConnaissanceDonneeByIdUsers(quizResultDTO.getUserId());
            if (connaissanceDonnee!=null){
                connaissanceDonnee.setIsFinished(true);
                connaissanceDonneeRepository.save(connaissanceDonnee);
            }
            userRepository.save(users);
        }

        // si le quiz est reussi, on passe a l'etape suivante. Pour Rapport activités
        QuizResult quizResultRapport = quizResultRepository.findQuizResultByTitre("RAPPORT", quizResultDTO.getUserId());
        if(quizResultRapport!=null && quizResultRapport.getIsUserSucceed()){
//          si tout est okay on met a jour l'etat
            Users users = userRepository.findByIdUser(quizResultDTO.getUserId());
            users.setIsEtapes5Done(true);
            //mettre a jour connaisance
            RapportEtonnement rapportEtonnement= rapportEtonnementService.findRapportEtonnementByIdUsers(quizResultDTO.getUserId());
            if (rapportEtonnement!=null){
                rapportEtonnement.setIsFinished(true);
                rapportEtonnementRepository.save(rapportEtonnement);
            }
            userRepository.save(users);
        }

        //vérification si le meme quizresult existe deja, si oui on le supprime et on cree un autre.
        if (quizResultTwo!=null)
            quizResultRepository.delete(quizResultTwo);
        //vérification si le meme quizresult existe deja, si oui on le supprime et on cree un autre.
        if (quizResultIntegration!=null)
            quizResultRepository.delete(quizResultIntegration);
        //vérification si le meme quizresult existe deja, si oui on le supprime et on cree un autre.
        if (quizResulTKce!=null)
            quizResultRepository.delete(quizResulTKce);
        //vérification si le meme quizresult existe deja, si oui on le supprime et on cree un autre.
        if (quizResultRapport!=null)
            quizResultRepository.delete(quizResultRapport);
        return quizResultRepository.save(quizResult);
    }

    @Override
    public QuizResult updateQuizResult(Long id, QuizResultDTO quizResultDTO) {
        QuizResult quizResultToUpdate = quizResultRepository.findByIdQuizResult(id);
        currentdate = new Date(currentTimeInMillis);
        if (quizResultToUpdate == null)
            throw new ApiRequestException("Quiz Result ID non trouvé");
        QuizResult quizResult = modelMapper.map(quizResultDTO, QuizResult.class);
        quizResult.setId(quizResultToUpdate.getId());
        quizResult.setTitre(quizResultDTO.getTitre());
        quizResult.setUsers(quizResultToUpdate.getUsers());
        //projet.setDatedebut(currentdate);
        // MAJ id users
        updateForeignKeyUsersQuiz(quizResultDTO, quizResult);
        return quizResultRepository.save(quizResult);
    }

    private void updateForeignKeyUsersQuiz(QuizResultDTO quizResultDTO, QuizResult quizResult) {
        // mettre a jour id Quiz si pas null
        if (quizResultDTO.getQuizId() != null )
            quizResult.setQuiz(quizService.findQuizById(quizResultDTO.getQuizId()));
        // mettre a jour id users si pas null
        if (quizResultDTO.getUserId() != null)
            quizResult.setUsers(userService.getUserById(quizResultDTO.getUserId()));
    }

    @Override
    public QuizResult findQuizResultById(Long id) {
        return quizResultRepository.findByIdQuizResult(id);
    }

    @Override
    public QuizResult findQuizResultByIdUsers(Long id) {
        Users users = userService.getUserById(id);
        if (users == null)
            throw new ApiRequestException("User non trouvé");
        return quizResultRepository.findQuizResultByIdUsers(id);
    }

    @Override
    public QuizResult findQuizResultTitre(String titre, Long id) {
        return quizResultRepository.findQuizResultByTitre(titre, id);
    }

    @Override
    public QuizResult findQuizResultByIdQuiz(Long id) {
        Quiz quiz = quizService.findQuizById(id);
        if (quiz == null)
            throw new ApiRequestException("Quiz non trouvé");
        return quizResultRepository.findQuizResultByIdQuiz(id);
    }

    @Override
    public List<QuizResult> listQuizResult() {
        return quizResultRepository.findAll();
    }

    @Override
    public void deleteQuizResultById(Long id) {
        QuizResult quizResult = quizResultRepository.findByIdQuizResult(id);
        if (quizResult==null)
            throw new ApiRequestException("ID Quiz Result non trouve");
        quizResultRepository.deleteById(id);
    }
}
