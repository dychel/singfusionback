package com.singfusion.singfusion.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class PresentationGenerale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "contenu_id", referencedColumnName = "id")
    private Contenus contenus;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;
    @OneToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;
    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;
    private Date dateAjout;
    private Date dateMaj;
    private Boolean isVideoWatched=false;
    private Boolean isPowerPointRead=false;
    private Boolean isFinished=false;

    public PresentationGenerale(Long id, String titre, String description, Contenus contenus, Users users, Quiz quiz, Document document, Date dateAjout, Date dateMaj, Boolean isVideoWatched, Boolean isPowerPointRead, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.contenus = contenus;
        this.users = users;
        this.quiz = quiz;
        this.document = document;
        this.dateAjout = dateAjout;
        this.dateMaj = dateMaj;
        this.isVideoWatched = isVideoWatched;
        this.isPowerPointRead = isPowerPointRead;
        this.isFinished = isFinished;
    }

    public PresentationGenerale(String titre, String description, Contenus contenus, Users users, Quiz quiz, Document document, Date dateAjout, Date dateMaj, Boolean isVideoWatched, Boolean isPowerPointRead, Boolean isFinished) {
        this.titre = titre;
        this.description = description;
        this.contenus = contenus;
        this.users = users;
        this.quiz = quiz;
        this.document = document;
        this.dateAjout = dateAjout;
        this.dateMaj = dateMaj;
        this.isVideoWatched = isVideoWatched;
        this.isPowerPointRead = isPowerPointRead;
        this.isFinished = isFinished;
    }

    public PresentationGenerale(Long id, String titre, String description, Users users, Quiz quiz, Document document, Date dateAjout, Date dateMaj, Boolean isVideoWatched, Boolean isPowerPointRead, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.users = users;
        this.quiz = quiz;
        this.document = document;
        this.dateAjout = dateAjout;
        this.dateMaj = dateMaj;
        this.isVideoWatched = isVideoWatched;
        this.isPowerPointRead = isPowerPointRead;
        this.isFinished = isFinished;
    }

    public PresentationGenerale(Long id, String titre, String description, Contenus contenus, Quiz quiz, Document document, Date dateAjout, Date dateMaj, Boolean isVideoWatched, Boolean isPowerPointRead, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.contenus = contenus;
        this.quiz = quiz;
        this.document = document;
        this.dateAjout = dateAjout;
        this.dateMaj = dateMaj;
        this.isVideoWatched = isVideoWatched;
        this.isPowerPointRead = isPowerPointRead;
        this.isFinished = isFinished;
    }

    public PresentationGenerale(Long id, String titre, String description, Contenus contenus, Users users, Document document, Date dateAjout, Date dateMaj, Boolean isVideoWatched, Boolean isPowerPointRead, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.contenus = contenus;
        this.users = users;
        this.document = document;
        this.dateAjout = dateAjout;
        this.dateMaj = dateMaj;
        this.isVideoWatched = isVideoWatched;
        this.isPowerPointRead = isPowerPointRead;
        this.isFinished = isFinished;
    }

    public PresentationGenerale(Long id, String titre, String description, Contenus contenus, Users users, Quiz quiz, Date dateAjout, Date dateMaj, Boolean isVideoWatched, Boolean isPowerPointRead, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.contenus = contenus;
        this.users = users;
        this.quiz = quiz;
        this.dateAjout = dateAjout;
        this.dateMaj = dateMaj;
        this.isVideoWatched = isVideoWatched;
        this.isPowerPointRead = isPowerPointRead;
        this.isFinished = isFinished;
    }

}
