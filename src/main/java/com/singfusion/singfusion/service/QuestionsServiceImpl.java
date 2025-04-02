package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.FormalitesDTO;
import com.singfusion.singfusion.dto.QuestionsDTO;
import com.singfusion.singfusion.entity.Formalites;
import com.singfusion.singfusion.entity.Questions;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.repository.QuestionsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    QuestionsRepository questionsRepository;
    @Autowired
    QuizService quizService;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public Questions saveQuestions(QuestionsDTO questionsDTO) {
        Questions questions = modelMapper.map(questionsDTO, Questions.class);
        return questionsRepository.save(questions);
    }

    private void updateForeignKeyQuiz(QuestionsDTO questionsDTO, Questions questions) {
        // mettre a jour id users si pas null
        if (questionsDTO.getQuizId()!= null )
            questions.setQuiz(quizService.findQuizById(questionsDTO.getQuizId()));
    }

    @Override
    public Questions updateQuestions(Long id, QuestionsDTO questionsDTO) {
        Questions questionsToUpdate = questionsRepository.findByIdQuestions(id);
        if (questionsToUpdate == null)
            throw new ApiRequestException("Questions non trouvée");
        //enregister les nouvelle infos
        Questions questions = modelMapper.map(questionsDTO, Questions.class);
        questions.setId(questionsToUpdate.getId());
        updateForeignKeyQuiz(questionsDTO, questions);
        return questionsRepository.save(questions);
    }

    @Override
    public Questions findQuestionsById(Long id) {
        return questionsRepository.findByIdQuestions(id);
    }
    @Override
    public List<Questions> listQuestions() {
        return questionsRepository.findAll();
    }

    @Override
    public List<Questions> listQuestionsByQuiz(Long id) {
        List<Questions> questions = questionsRepository.findByQuestionsByIdQuiz(id);
        if (questions==null)
            throw new ApiRequestException("Pas de question pour ce quiz");
        return questions;
    }

    @Override
    public void deleteQuestionsById(Long id) {
        questionsRepository.deleteById(id);
    }
}
