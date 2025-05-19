package com.singfusion.singfusion.repository;
import com.singfusion.singfusion.entity.OtpUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public interface OtpRepository extends JpaRepository<OtpUsers,Long> {
    @Query("select otpUsers from OtpUsers otpUsers where otpUsers.users.id = :id")
    OtpUsers findOtpByUser(@PathVariable("id") Long id);
}
