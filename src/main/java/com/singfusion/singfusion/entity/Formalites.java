package com.singfusion.singfusion.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Formalites {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users users;
    private String titre;
    private String descritpion;
    private Date date_ajout;
    @OneToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;
    private Boolean AsKit=false;
    private Boolean AsAccess=false;
    private Boolean AsOutilsInfo=false;
    private Boolean AsPoste=false;
    private Boolean AsVisited=false;
    private Boolean IsFinished=false;

    public Formalites(Long id, Users users, String titre, String descritpion, Date date_ajout, Quiz quiz, Boolean asKit, Boolean asAccess, Boolean asOutilsInfo, Boolean asPoste, Boolean asVisited, Boolean isFinished) {
        this.id = id;
        this.users = users;
        this.titre = titre;
        this.descritpion = descritpion;
        this.date_ajout = date_ajout;
        this.quiz = quiz;
        AsKit = asKit;
        AsAccess = asAccess;
        AsOutilsInfo = asOutilsInfo;
        AsPoste = asPoste;
        AsVisited = asVisited;
        IsFinished = isFinished;
    }

    public Formalites(Users users, String titre, String descritpion, Date date_ajout, Quiz quiz, Boolean asKit, Boolean asAccess, Boolean asOutilsInfo, Boolean asPoste, Boolean asVisited, Boolean isFinished) {
        this.users = users;
        this.titre = titre;
        this.descritpion = descritpion;
        this.date_ajout = date_ajout;
        this.quiz = quiz;
        AsKit = asKit;
        AsAccess = asAccess;
        AsOutilsInfo = asOutilsInfo;
        AsPoste = asPoste;
        AsVisited = asVisited;
        IsFinished = isFinished;
    }

    public Formalites(Long id, String titre, String descritpion, Date date_ajout, Boolean asKit, Boolean asAccess, Boolean asOutilsInfo, Boolean asPoste, Boolean asVisited, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.descritpion = descritpion;
        this.date_ajout = date_ajout;
        AsKit = asKit;
        AsAccess = asAccess;
        AsOutilsInfo = asOutilsInfo;
        AsPoste = asPoste;
        AsVisited = asVisited;
        IsFinished = isFinished;
    }

    public Formalites(Long id, Users users, String titre, String descritpion, Date date_ajout, Boolean asKit, Boolean asAccess, Boolean asOutilsInfo, Boolean asPoste, Boolean asVisited, Boolean isFinished) {
        this.id = id;
        this.users = users;
        this.titre = titre;
        this.descritpion = descritpion;
        this.date_ajout = date_ajout;
        AsKit = asKit;
        AsAccess = asAccess;
        AsOutilsInfo = asOutilsInfo;
        AsPoste = asPoste;
        AsVisited = asVisited;
        IsFinished = isFinished;
    }

    public Formalites(Long id, String titre, String descritpion, Date date_ajout, Quiz quiz, Boolean asKit, Boolean asAccess, Boolean asOutilsInfo, Boolean asPoste, Boolean asVisited, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.descritpion = descritpion;
        this.date_ajout = date_ajout;
        this.quiz = quiz;
        AsKit = asKit;
        AsAccess = asAccess;
        AsOutilsInfo = asOutilsInfo;
        AsPoste = asPoste;
        AsVisited = asVisited;
        IsFinished = isFinished;
    }
}
