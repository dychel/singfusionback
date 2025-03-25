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
public class Reponses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenu;
    private Etapes etape_integration;
    private String description;
    private Date date_ajout;

    @ManyToOne
    @JoinColumn(name = "questions_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Questions questions;

    public Reponses(Long id, String contenu, Etapes etape_integration, String description, Date date_ajout, Questions questions) {
        this.id = id;
        this.contenu = contenu;
        this.etape_integration = etape_integration;
        this.description = description;
        this.date_ajout = date_ajout;
        this.questions = questions;
    }

    public Reponses(String contenu, Etapes etape_integration, String description, Date date_ajout, Questions questions) {
        this.contenu = contenu;
        this.etape_integration = etape_integration;
        this.description = description;
        this.date_ajout = date_ajout;
        this.questions = questions;
    }

    public Reponses(Long id, String contenu, Etapes etape_integration, String description, Date date_ajout) {
        this.id = id;
        this.contenu = contenu;
        this.etape_integration = etape_integration;
        this.description = description;
        this.date_ajout = date_ajout;
    }
}
