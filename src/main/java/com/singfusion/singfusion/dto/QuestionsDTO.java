package com.singfusion.singfusion.dto;
import com.singfusion.singfusion.config.Etapes;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class QuestionsDTO {

    private Long id;
    private String contenu;
    private Etapes etape_integration;
    private String description;
    private Date date_ajout;
    private Long quizId;
    private List<Long> reponsesIDs;
}
