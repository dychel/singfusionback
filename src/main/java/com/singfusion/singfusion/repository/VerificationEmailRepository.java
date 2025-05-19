package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.VerificationEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface VerificationEmailRepository extends JpaRepository<VerificationEmail, Long> {
    @Query("select verificationEmail from VerificationEmail verificationEmail where verificationEmail.users.id = :id")
    VerificationEmail findOtpByUser(@PathVariable("id") Long id);
}
