package com.singfusion.singfusion.entity;
import com.singfusion.singfusion.config.Etapes;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class IntegrationMetier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Etapes etapes;
    @OneToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "contenu_id", referencedColumnName = "id")
    private Contenus contenus;
    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;
    //objet document a prevoir, en phase de reflexion
    private Date dateajout;
    private Boolean isVideoWatched=false;
    private Boolean isPowerPointRead=false;

    public IntegrationMetier(Long id, String titre, String description, Etapes etapes, Quiz quiz, Users users, Contenus contenus, Document document, Date dateajout, Boolean isVideoWatched, Boolean isPowerPointRead) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.etapes = etapes;
        this.quiz = quiz;
        this.users = users;
        this.contenus = contenus;
        this.document = document;
        this.dateajout = dateajout;
        this.isVideoWatched = isVideoWatched;
        this.isPowerPointRead = isPowerPointRead;
    }

    public IntegrationMetier(String titre, String description, Etapes etapes, Quiz quiz, Users users, Contenus contenus, Document document, Date dateajout, Boolean isVideoWatched, Boolean isPowerPointRead) {
        this.titre = titre;
        this.description = description;
        this.etapes = etapes;
        this.quiz = quiz;
        this.users = users;
        this.contenus = contenus;
        this.document = document;
        this.dateajout = dateajout;
        this.isVideoWatched = isVideoWatched;
        this.isPowerPointRead = isPowerPointRead;
    }

    public IntegrationMetier(Long id, String titre, String description, Etapes etapes, Users users, Contenus contenus, Document document, Date dateajout, Boolean isVideoWatched, Boolean isPowerPointRead) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.etapes = etapes;
        this.users = users;
        this.contenus = contenus;
        this.document = document;
        this.dateajout = dateajout;
        this.isVideoWatched = isVideoWatched;
        this.isPowerPointRead = isPowerPointRead;
    }

    public IntegrationMetier(Long id, String titre, String description, Etapes etapes, Quiz quiz, Contenus contenus, Document document, Date dateajout, Boolean isVideoWatched, Boolean isPowerPointRead) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.etapes = etapes;
        this.quiz = quiz;
        this.contenus = contenus;
        this.document = document;
        this.dateajout = dateajout;
        this.isVideoWatched = isVideoWatched;
        this.isPowerPointRead = isPowerPointRead;
    }

    public IntegrationMetier(Long id, String titre, String description, Etapes etapes, Quiz quiz, Users users, Document document, Date dateajout, Boolean isVideoWatched, Boolean isPowerPointRead) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.etapes = etapes;
        this.quiz = quiz;
        this.users = users;
        this.document = document;
        this.dateajout = dateajout;
        this.isVideoWatched = isVideoWatched;
        this.isPowerPointRead = isPowerPointRead;
    }

    public IntegrationMetier(Long id, String titre, String description, Etapes etapes, Quiz quiz, Users users, Contenus contenus, Date dateajout, Boolean isVideoWatched, Boolean isPowerPointRead) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.etapes = etapes;
        this.quiz = quiz;
        this.users = users;
        this.contenus = contenus;
        this.dateajout = dateajout;
        this.isVideoWatched = isVideoWatched;
        this.isPowerPointRead = isPowerPointRead;
    }
}
