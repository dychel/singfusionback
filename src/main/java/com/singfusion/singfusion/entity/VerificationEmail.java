package com.singfusion.singfusion.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VerificationEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codeOtp;
    private String email;
    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users users;
    @Column(name="created_at")
    @CreationTimestamp
    private String createdAt;
    private String messageMail;
    private Boolean isVerified=false;
}
