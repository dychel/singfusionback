package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.OutilsInformatique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OutilsInformatiqueReposittory extends JpaRepository<OutilsInformatique, Long> {

    @Query("select outilsInformatique from OutilsInformatique outilsInformatique where outilsInformatique.id = :id")
    OutilsInformatique findByIdOutilsInformatique(@Param("id") Long id);

    @Query("select outilsInformatique from OutilsInformatique outilsInformatique where outilsInformatique.id != :id")
    OutilsInformatique findByIdDifferentoutilsInformatique(@Param("id") Long id);
}
