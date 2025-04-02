package com.singfusion.singfusion.dto;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
public class FormalitesDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String titre;
    private String descritpion;
    private Date date_ajout;
    private Long quizId;
    private Boolean AsKit=false;
    private Boolean AsAccess=false;
    private Boolean AsOutilsInfo=false;
    private Boolean AsPoste=false;
    private Boolean AsVisited=false;
    private Boolean IsFinished=false;
}
