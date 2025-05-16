package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.Contenus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenusRepository extends JpaRepository<Contenus,Long> {


    @Query("select contenus from Contenus contenus where contenus.id = :id")
    Contenus findByIdContenus(@Param("id") Long id);
}
