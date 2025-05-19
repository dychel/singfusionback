package com.singfusion.singfusion.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class RapportEtonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Users users;
    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;
    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;
    private Date DateAjout;
    private Boolean isFinished=false;

    public RapportEtonnement(Long id, String titre, String description, Users users, Document document, Quiz quiz, Date dateAjout, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.users = users;
        this.document = document;
        this.quiz = quiz;
        DateAjout = dateAjout;
        this.isFinished = isFinished;
    }

    public RapportEtonnement(String titre, String description, Users users, Document document, Quiz quiz, Date dateAjout, Boolean isFinished) {
        this.titre = titre;
        this.description = description;
        this.users = users;
        this.document = document;
        this.quiz = quiz;
        DateAjout = dateAjout;
        this.isFinished = isFinished;
    }

    public RapportEtonnement(Long id, String titre, String description, Document document, Quiz quiz, Date dateAjout, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.document = document;
        this.quiz = quiz;
        DateAjout = dateAjout;
        this.isFinished = isFinished;
    }

    public RapportEtonnement(Long id, String titre, String description, Users users, Quiz quiz, Date dateAjout, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.users = users;
        this.quiz = quiz;
        DateAjout = dateAjout;
        this.isFinished = isFinished;
    }

    public RapportEtonnement(Long id, String titre, String description, Users users, Document document, Date dateAjout, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.users = users;
        this.document = document;
        DateAjout = dateAjout;
        this.isFinished = isFinished;
    }
}
