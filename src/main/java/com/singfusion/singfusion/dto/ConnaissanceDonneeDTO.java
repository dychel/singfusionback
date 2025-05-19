package com.singfusion.singfusion.dto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.Date;

@Data
public class ConnaissanceDonneeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Long documentId;
    private Long userId;
    private String objectif;
    private String contexte;
    private String methodologieConsultation;
    private String conseil;
    private Long quizId;
    private Date dateAjout;
}
