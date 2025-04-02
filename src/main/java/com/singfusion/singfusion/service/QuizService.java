package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.QuizDTO;
import com.singfusion.singfusion.entity.Questions;
import com.singfusion.singfusion.entity.Quiz;
import java.util.List;

public interface QuizService {

    Quiz saveQuiz(QuizDTO quizDTO);
    Quiz updateQuiz(Long id, QuizDTO quizDTO);
    Quiz findQuizById(Long id);
    List<Quiz> listQuiz();
    void deleteQuizById(Long id);
}
