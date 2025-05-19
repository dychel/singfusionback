package com.singfusion.singfusion.service;
import com.singfusion.singfusion.dto.QuestionsDTO;
import com.singfusion.singfusion.entity.Questions;
import java.util.List;

public interface QuestionsService {

    Questions saveQuestions(QuestionsDTO questionsDTO);
    Questions updateQuestions(Long id, QuestionsDTO questionsDTO);
    Questions findQuestionsById(Long id);
    List<Questions> listQuestions();
   // List<Questions> listQuestionsByQuiz(Long id);
    void deleteQuestionsById(Long id);
}
