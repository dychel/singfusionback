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
    private String titre;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "quiz_question",
            joinColumns = {
                    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "question_id", referencedColumnName = "id")
            }
    )

    private List<Questions> questions;
    private String description;
    private String etape_integration;
    private Date date_ajout;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Users users;
    private Long totalQuestion;

    public Quiz(Long id, String titre, List<Questions> questions, String description, String etape_integration, Date date_ajout, Users users, Long totalQuestion) {
        this.id = id;
        this.titre = titre;
        this.questions = questions;
        this.description = description;
        this.etape_integration = etape_integration;
        this.date_ajout = date_ajout;
        this.users = users;
        this.totalQuestion = totalQuestion;
    }

    public Quiz(String titre, List<Questions> questions, String description, String etape_integration, Date date_ajout, Users users, Long totalQuestion) {
        this.titre = titre;
        this.questions = questions;
        this.description = description;
        this.etape_integration = etape_integration;
        this.date_ajout = date_ajout;
        this.users = users;
        this.totalQuestion = totalQuestion;
    }

    public Quiz(Long id, String titre, String description, String etape_integration, Date date_ajout, Users users, Long totalQuestion) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.etape_integration = etape_integration;
        this.date_ajout = date_ajout;
        this.users = users;
        this.totalQuestion = totalQuestion;
    }

    public Quiz(Long id, String titre, List<Questions> questions, String description, String etape_integration, Date date_ajout, Long totalQuestion) {
        this.id = id;
        this.titre = titre;
        this.questions = questions;
        this.description = description;
        this.etape_integration = etape_integration;
        this.date_ajout = date_ajout;
        this.totalQuestion = totalQuestion;
    }
}
