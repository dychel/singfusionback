package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.PresentationGenerale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationGeneraleRepository extends JpaRepository<PresentationGenerale,Long> {

    @Query("select presentationGenerale from PresentationGenerale presentationGenerale where presentationGenerale.id = :id")
    PresentationGenerale findByIdPresentationGenerale(@Param("id") Long id);

    @Query("select presentationGenerale from PresentationGenerale presentationGenerale where presentationGenerale.id != :id")
    PresentationGenerale findByIdDifferentPresentationGenerale(@Param("id") Long id);

    @Query("select presentationGenerale from PresentationGenerale presentationGenerale where presentationGenerale.users.id = :id")
    PresentationGenerale findPresentationGeneraleByIdUsers(@Param("id") Long id);

    @Query("select presentationGenerale from PresentationGenerale presentationGenerale where presentationGenerale.contenus.id = :id")
    PresentationGenerale findPresentationGeneraleByIdContenus(@Param("id") Long id);

    @Query("select presentationGenerale from PresentationGenerale presentationGenerale where presentationGenerale.quiz.id = :id")
    PresentationGenerale findPresentationGeneraleByIdQuiz(@Param("id") Long id);
}
