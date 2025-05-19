package com.singfusion.singfusion.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
public class OtpDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codeOtp;
    private String numero_telephone;
    private String password;
    @Column(name="created_at")
    @CreationTimestamp
    private String createdAt;
}
