package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.ConnaissanceDonnee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnaissanceDonneeRepository extends JpaRepository<ConnaissanceDonnee,Long> {

    @Query("select connaissanceDonnee from ConnaissanceDonnee connaissanceDonnee where connaissanceDonnee.id = :id")
    ConnaissanceDonnee findByIdConnaissanceDonnee(@Param("id") Long id);
    @Query("select connaissanceDonnee from ConnaissanceDonnee connaissanceDonnee where connaissanceDonnee.id != :id")
    ConnaissanceDonnee findByIdDifferentconnaissanceDonnee(@Param("id") Long id);
    @Query("select connaissanceDonnee from ConnaissanceDonnee connaissanceDonnee where connaissanceDonnee.users.id = :id")
    ConnaissanceDonnee findConnaissanceDonneeByIdUsers(@Param("id") Long id);
    @Query("select connaissanceDonnee from ConnaissanceDonnee connaissanceDonnee where connaissanceDonnee.quiz.id = :id")
    ConnaissanceDonnee findConnaissanceDonneeByIdQuiz(@Param("id") Long id);
    @Query("select connaissanceDonnee from ConnaissanceDonnee connaissanceDonnee where connaissanceDonnee.document.id = :id")
    ConnaissanceDonnee findConnaissanceDonneeByIdDocument(@Param("id") Long id);
}
