package com.singfusion.singfusion.dto;
import com.singfusion.singfusion.config.Etapes;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class QuizDTO {
    private Long id;
    private String titre;
    private List<Long> questionsIds;
    private String description;
    private Etapes etape_integration;
    private Date date_ajout;
    private Long userId;
}
