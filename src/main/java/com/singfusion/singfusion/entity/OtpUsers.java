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
public class OtpUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codeOtp;
    private String numero_telephone;
    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users users;
    @Column(name="created_at")
    @CreationTimestamp
    private String createdAt;
}
