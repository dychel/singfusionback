package com.singfusion.singfusion.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.singfusion.singfusion.config.Etapes;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "quiz_questions",
            joinColumns = {
                    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "questions_id", referencedColumnName = "id")
            }
    )
    private List<Questions> lesquestions;
    private String description;
    private Etapes etape_integration;
    private Date date_ajout;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Users users;

    public Quiz(Long id, List<Questions> lesquestions, String description, Etapes etape_integration, Date date_ajout, Users users) {
        this.id = id;
        this.lesquestions = lesquestions;
        this.description = description;
        this.etape_integration = etape_integration;
        this.date_ajout = date_ajout;
        this.users = users;
    }

    public Quiz(List<Questions> lesquestions, String description, Etapes etape_integration, Date date_ajout, Users users) {
        this.lesquestions = lesquestions;
        this.description = description;
        this.etape_integration = etape_integration;
        this.date_ajout = date_ajout;
        this.users = users;
    }

    public Quiz(Long id, List<Questions> lesquestions, String description, Etapes etape_integration, Date date_ajout) {
        this.id = id;
        this.lesquestions = lesquestions;
        this.description = description;
        this.etape_integration = etape_integration;
        this.date_ajout = date_ajout;
    }

    public Quiz(Long id, String description, Etapes etape_integration, Date date_ajout, Users users) {
        this.id = id;
        this.description = description;
        this.etape_integration = etape_integration;
        this.date_ajout = date_ajout;
        this.users = users;
    }

    public Quiz(Long id, String description, Etapes etape_integration, Date date_ajout) {
        this.id = id;
        this.description = description;
        this.etape_integration = etape_integration;
        this.date_ajout = date_ajout;
    }
}
