package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.Acces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesRepository extends JpaRepository<Acces, Long> {

    @Query("select acces from Acces acces where acces.id = :id")
    Acces findByIdAcces(@Param("id") Long id);

    @Query("select acces from Acces acces where acces.id != :id")
    Acces findByIdDifferentAcces(@Param("id") Long id);
}
