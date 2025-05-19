package com.singfusion.singfusion.dto;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
public class ProjetDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String intitule;
    private String description;
    private Date datedebut;
    private Date datefin;
    private Long documentId;
    private Long userId;

}
