package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.QuizDTO;
import com.singfusion.singfusion.entity.Questions;
import com.singfusion.singfusion.entity.Quiz;
import com.singfusion.singfusion.entity.Reponses;
import com.singfusion.singfusion.repository.QuizRepository;
import com.singfusion.singfusion.repository.ReponsesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public Quiz saveQuiz(QuizDTO quizDTO) {
        Quiz quiz = modelMapper.map(quizDTO, Quiz.class);
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Long id, QuizDTO quizDTO) {
        return null;
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
}
