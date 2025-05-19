package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {

    @Query("select projet from Projet projet where projet.id = :id")
    Projet findByIdProjet(@Param("id") Long id);
    @Query("select projet from Projet projet where projet.id != :id")
    Projet findByIdDifferentProjet(@Param("id") Long id);
    @Query("select projet from Projet projet where projet.users.id = :id")
    Projet findProjetByIdUsers(@Param("id") Long id);
    @Query("select projet from Projet projet where projet.document.id = :id")
    Projet findProjetByIdDocument(@Param("id") Long id);
}
