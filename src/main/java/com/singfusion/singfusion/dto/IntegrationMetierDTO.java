package com.singfusion.singfusion.dto;
import com.singfusion.singfusion.config.Etapes;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.Date;

@Data
public class IntegrationMetierDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Etapes etapes;
    private Long quizId;
    private Long userId;
    private Long contenuId;
    //objet document a prevoir, en phase de reflexion
    private Date dateajout;
    private Boolean isVideoWatched=false;
    private Boolean isPowerPointRead=false;
}
