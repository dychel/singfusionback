package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.Formalites;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormalitesRepository {

    @Query("select formalites from Formalites formalites where formalites.id = :id")
    Formalites findByIdFormalites(@Param("id") Long id);

    @Query("select formalites from Formalites formalites where formalites.id != :id")
    Formalites findByIdDifferentFormalites(@Param("id") Long id);
}
