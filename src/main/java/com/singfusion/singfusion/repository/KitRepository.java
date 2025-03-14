package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.Kit;
import com.singfusion.singfusion.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KitRepository extends JpaRepository<Kit, Long> {
    @Query("select kit from Kit kit where kit.id = :id")
    Kit findByIdKit(@Param("id") Long id);

    @Query("select kit from Kit kit where kit.id != :id")
    Role findByIdDifferentKit(@Param("id") Long id);
}
