package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.Postetravail;
import com.singfusion.singfusion.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {

    @Query("select questions from Questions questions where questions.id = :id")
    Questions findByIdQuestions(@Param("id") Long id);
    @Query("select questions from Questions questions where questions.id != :id")
    Questions findByIdDifferentQuestions(@Param("id") Long id);
    @Query("select questions from Questions questions where questions.quiz.id = :id")
    List<Questions> findByQuestionsByIdQuiz(@Param("id") Long id);
}
