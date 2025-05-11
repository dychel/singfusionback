package com.singfusion.singfusion.dto;
import com.singfusion.singfusion.config.Etapes;
import com.singfusion.singfusion.config.TypeContenu;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.File;
import java.util.Date;

@Data
public class DocumentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String intutile;
    private TypeContenu typeContenu;
    private Date dateAjout;
    private Etapes etapes;
    private File file;
    private Long numeroEtape;
    private Long usersId;
}
