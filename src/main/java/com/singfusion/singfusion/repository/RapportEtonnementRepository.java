package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.RapportEtonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RapportEtonnementRepository extends JpaRepository<RapportEtonnement, Long> {

}
