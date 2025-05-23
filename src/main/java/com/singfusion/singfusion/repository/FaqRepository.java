package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {

    @Query("select faq from Faq faq where faq.id = :id")
    Faq findByIdFaq(@Param("id") Long id);
    @Query("select faq from Faq faq where faq.users.id = :id")
    Faq findFaqByIdUsers(@Param("id") Long id);
}
