package com.singfusion.singfusion.dto;
import com.singfusion.singfusion.entity.Users;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.Date;

@Data
public class PresentationGeneraleDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Long contenusId;
    private Long userId;
    private Long quizId;
    private Date dateAjout;
    private Date dateMaj;
    private Boolean isVideoWatched=false;
    private Boolean isPowerPointRead=false;
}
