package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult,Long> {
    @Query("select quizResult from QuizResult quizResult where quizResult.id = :id")
    QuizResult findByIdQuizResult(@Param("id") Long id);
    @Query("select quizResult from QuizResult quizResult where quizResult.id != :id")
    QuizResult findByIdDifferentQuizResult(@Param("id") Long id);
    @Query("select quizResult from QuizResult quizResult where quizResult.users.id = :id")
    QuizResult findQuizResultByIdUsers(@Param("id") Long id);
    @Query("select quizResult from QuizResult quizResult where quizResult.quiz.id = :id")
    QuizResult findQuizResultByIdQuiz(@Param("id") Long id);
}
