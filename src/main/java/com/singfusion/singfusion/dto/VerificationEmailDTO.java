package com.singfusion.singfusion.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
public class VerificationEmailDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codeOtp;
    private String email;
    private Long userId;
    @Column(name="created_at")
    @CreationTimestamp
    private String createdAt;
    private String messageMail;
    private Boolean isVerified=false;
}
