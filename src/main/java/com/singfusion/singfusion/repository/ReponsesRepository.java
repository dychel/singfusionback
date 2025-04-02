package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.Reponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Repository
public interface ReponsesRepository extends JpaRepository<Reponses, Long> {

    @Query("select reponses from Reponses reponses where reponses.id = :id")
    Reponses findByIdReponses(@Param("id") Long id);

    @Query("select reponses from Reponses reponses where reponses.questions.id = :id")
    List<Reponses> findReponsesByQuestions(@PathVariable("id") Long id);

    @Query("select reponses from Reponses reponses where reponses.id != :id")
    Reponses findByIdDifferentReponses(@Param("id") Long id);
}
