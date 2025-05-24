package com.singfusion.singfusion.dto;
import com.singfusion.singfusion.config.Etapes;
import lombok.Data;
import java.util.Date;

@Data
public class ReponsesDTO {

    private Long id;
    private String contenu;
    private String etape_integration;
    private String description;
    private Date date_ajout;
    private Long questionId;
    public Boolean isCorrect;
}
