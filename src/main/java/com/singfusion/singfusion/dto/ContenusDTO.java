package com.singfusion.singfusion.dto;
import com.singfusion.singfusion.config.TypeContenu;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.Date;

@Data
public class ContenusDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String intutile;
    private TypeContenu typeContenu;
    private Date dateAjout;
}
