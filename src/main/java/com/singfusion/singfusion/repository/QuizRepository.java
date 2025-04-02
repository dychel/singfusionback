package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.Questions;
import com.singfusion.singfusion.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {

    @Query("select quiz from Quiz quiz where quiz.id = :id")
    Quiz findByIdQuiz(@Param("id") Long id);

    @Query("select quiz from Quiz quiz where quiz.id != :id")
    Quiz findByIdDifferentQuiz(@Param("id") Long id);

}
