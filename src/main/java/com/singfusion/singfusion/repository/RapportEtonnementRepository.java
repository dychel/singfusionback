package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.Projet;
import com.singfusion.singfusion.entity.RapportEtonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RapportEtonnementRepository extends JpaRepository<RapportEtonnement, Long> {
    @Query("select rapportEtonnement from RapportEtonnement rapportEtonnement where rapportEtonnement.id = :id")
    RapportEtonnement findByIdRapport(@Param("id") Long id);
    @Query("select rapportEtonnement from RapportEtonnement rapportEtonnement where rapportEtonnement.id != :id")
    RapportEtonnement findByIdDifferentRapport(@Param("id") Long id);
    @Query("select rapportEtonnement from RapportEtonnement rapportEtonnement where rapportEtonnement.users.id = :id")
    RapportEtonnement findRapportByIdUsers(@Param("id") Long id);
    @Query("select rapportEtonnement from RapportEtonnement rapportEtonnement where rapportEtonnement.document.id = :id")
    RapportEtonnement findEtonnementByIdDocument(@Param("id") Long id);
    @Query("select rapportEtonnement from RapportEtonnement rapportEtonnement where rapportEtonnement.quiz.id = :id")
    RapportEtonnement findRapportEtonnementByIdQuiz(@Param("id") Long id);
}
