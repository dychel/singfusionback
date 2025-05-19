package com.singfusion.singfusion.service;
import com.singfusion.singfusion.config.Etapes;
import com.singfusion.singfusion.dto.QuizDTO;
import com.singfusion.singfusion.entity.Questions;
import com.singfusion.singfusion.entity.Quiz;
import com.singfusion.singfusion.entity.Reponses;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.repository.QuestionsRepository;
import com.singfusion.singfusion.repository.QuizRepository;
import com.singfusion.singfusion.repository.ReponsesRepository;
import com.singfusion.singfusion.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionsRepository questionsRepository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;
    @Override
    public Quiz saveQuiz(QuizDTO quizDTO) {

        Quiz quiz = modelMapper.map(quizDTO, Quiz.class);
        List<Long> list_dto= quizDTO.getQuestionsIds();
        // Find a list by all id and set speciality value in user
        if (!list_dto.isEmpty()){
            List<Questions> List_questions = questionsRepository.findAllById(list_dto);
            quiz.setQuestions(List_questions);
        }
        Users users = userRepository.findByIdUser(quizDTO.getUserId());
        if (users!=null){
            quiz.setUsers(users);
        }
        return quizRepository.save(quiz);
    }
    @Override
    public Quiz updateQuiz(Long id, QuizDTO quizDTO) {
        Quiz quizToUpdate = quizRepository.findByIdQuiz(id);
        if (quizToUpdate == null)
            return null;
        Quiz quiz = modelMapper.map(quizDTO, Quiz.class);
        // Get list questions set in users dto as an array, speciality=activity
        List<Long> list_dto= quizDTO.getQuestionsIds();
        // Find a list by all id and set questions value in user
        if (!list_dto.isEmpty()){
            List<Questions> List_questions = questionsRepository.findAllById(list_dto);
            quiz.setQuestions(List_questions);
        }else{
            quiz.setQuestions(quizToUpdate.getQuestions());
        }
//        verification id user
        if (quizDTO.getUserId() != null)
            quiz.setUsers(userRepository.findByIdUser(quizDTO.getUserId()));
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz findQuizById(Long id) {
        return quizRepository.findByIdQuiz(id);
    }

    @Override
    public List<Quiz> listQuiz() {
        return quizRepository.findAll();
    }

    @Override
    public void deleteQuizById(Long id) {
        quizRepository.deleteById(id);
    }

    @Override
    public List<Quiz> getByEtapes(String etape_integration) {
        return quizRepository.findQuizByEtapes(etape_integration);
    }
}
