package com.singfusion.singfusion.dto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class RapportEtonnementDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Long userId;
    private Long documentId;
    private Long quizId;
    private Date DateAjout;
    private MultipartFile fichier;
    private Boolean isFinished=false;
}
