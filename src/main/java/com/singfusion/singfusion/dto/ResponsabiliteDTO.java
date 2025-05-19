package com.singfusion.singfusion.dto;
import lombok.Data;
import java.util.Date;

@Data
public class ResponsabiliteDTO {
    private Long id;
    private String titre;
    private Long userId;
    private String description;
    private Date date_ajout;
}
