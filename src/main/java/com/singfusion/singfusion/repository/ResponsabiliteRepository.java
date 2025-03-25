package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.Formalites;
import com.singfusion.singfusion.entity.Responsabilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsabiliteRepository extends JpaRepository<Responsabilite, Long> {

    @Query("select responsabilite from Responsabilite responsabilite where responsabilite.id = :id")
    Responsabilite findByIdResponsabilite(@Param("id") Long id);

    @Query("select responsabilite from Responsabilite responsabilite where responsabilite.id != :id")
    Responsabilite findByIdDifferentResponsabilite(@Param("id") Long id);
}
