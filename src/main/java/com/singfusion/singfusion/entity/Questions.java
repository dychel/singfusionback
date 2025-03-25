package com.singfusion.singfusion.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.singfusion.singfusion.config.Etapes;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenu;
    private Etapes etape_integration;
    private String description;
    private Date date_ajout;

    public Questions(Long id, String contenu, Etapes etape_integration, String description, Date date_ajout) {
        this.id = id;
        this.contenu = contenu;
        this.etape_integration = etape_integration;
        this.description = description;
        this.date_ajout = date_ajout;
    }

    public Questions(String contenu, Etapes etape_integration, String description, Date date_ajout) {
        this.contenu = contenu;
        this.etape_integration = etape_integration;
        this.description = description;
        this.date_ajout = date_ajout;
    }
}
