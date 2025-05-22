package com.singfusion.singfusion.dto;
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
    //
    private Boolean isVideoWatched=false;
    private Boolean isVideoWatched2=false;
    private Boolean isVideoWatched3=false;
    private Boolean isVideoWatched4=false;
    //
    private Boolean isPowerPointRead=false;
    private Boolean isPowerPointRead2=false;
    private Boolean isPowerPointRead3=false;
    private Boolean isPowerPointRead4=false;
}
