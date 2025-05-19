package com.singfusion.singfusion.dto;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
public class FaqDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String question;
    private String reponses;
    private Long userId;
    private String categorie;
    private Date date;
}
