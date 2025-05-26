package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.QuizResultDTO;
import com.singfusion.singfusion.entity.Quiz;
import com.singfusion.singfusion.entity.QuizResult;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.QuizResultRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
    QuizResultRepository quizResultRepository;
    Date currentdate;
    Long currentTimeInMillis = System.currentTimeMillis();
    @Override
    public QuizResult saveQuizResult(QuizResultDTO quizResultDTO) {
        QuizResult quizResult = modelMapper.map(quizResultDTO, QuizResult.class);
        if (quizResultDTO.getUserId() != null)
            quizResult.setUsers(userService.getUserById(quizResultDTO.getUserId()));
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
    public List<QuizResult> findQuizResultTitre(String titre, Long id) {
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
