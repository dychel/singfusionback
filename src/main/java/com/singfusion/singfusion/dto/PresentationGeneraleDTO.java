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
    private Boolean isVideoWatched;
    private Boolean isVideoWatched2;
    private Boolean isVideoWatched3;
    private Boolean isVideoWatched4;
    //
    private Boolean isPowerPointRead;
    private Boolean isPowerPointRead2;
    private Boolean isPowerPointRead3;
    private Boolean isPowerPointRead4;
    private Boolean isFinished;
}
