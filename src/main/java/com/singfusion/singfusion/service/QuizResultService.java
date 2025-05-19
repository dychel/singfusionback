package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.QuizResultDTO;
import com.singfusion.singfusion.entity.QuizResult;
import java.util.List;

public interface QuizResultService {

    QuizResult saveQuizResult(QuizResultDTO quizResultDTO);
    QuizResult updateQuizResult(Long id, QuizResultDTO quizResultDTO);
    QuizResult findQuizResultById(Long id);
    QuizResult findQuizResultByIdUsers(Long id);
    QuizResult findQuizResultByIdQuiz(Long id);
    List<QuizResult> listQuizResult();
    void deleteQuizResultById(Long id);
}
