package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.IntegrationMetier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IntegrationMetierRepository extends JpaRepository<IntegrationMetier,Long> {

    @Query("select integrationMetier from IntegrationMetier integrationMetier where integrationMetier.id = :id")
    IntegrationMetier findByIdIntegrationMetier(@Param("id") Long id);

    @Query("select integrationMetier from IntegrationMetier integrationMetier where integrationMetier.id != :id")
    IntegrationMetier findByIdDifferentIntegrationMetier(@Param("id") Long id);

    @Query("select integrationMetier from IntegrationMetier integrationMetier where integrationMetier.users.id = :id")
    IntegrationMetier findIntegrationMetierByIdUsers(@Param("id") Long id);

    @Query("select integrationMetier from IntegrationMetier integrationMetier where integrationMetier.contenus.id = :id")
    IntegrationMetier findIntegrationMetierByIdContenus(@Param("id") Long id);

    @Query("select integrationMetier from IntegrationMetier integrationMetier where integrationMetier.quiz.id = :id")
    IntegrationMetier findIntegrationMetierByIdQuiz(@Param("id") Long id);
}
