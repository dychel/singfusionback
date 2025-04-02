package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.Postetravail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteRepository extends JpaRepository<Postetravail,Long> {

    @Query("select postetravail from Postetravail postetravail where postetravail.id = :id")
    Postetravail findByIdPosteTravail(@Param("id") Long id);

    @Query("select postetravail from Postetravail postetravail where postetravail.id != :id")
    Postetravail findByIdDifferentPosteTravail(@Param("id") Long id);
}
