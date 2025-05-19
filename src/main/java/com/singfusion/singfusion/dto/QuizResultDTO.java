package com.singfusion.singfusion.dto;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
public class QuizResultDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private Long userId;
    private Long quizId;
    private Boolean isUserSucceed;
    private Boolean isUserFailed;
    private Long totalQuestion;
    private Long totalEchec;
    private Float pourcentage;
    private Date dateAjout;
}
