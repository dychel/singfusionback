package com.singfusion.singfusion.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;
    private Boolean isUserSucceed;
    private Boolean isUserFailed;
    private Long totalQuestion;
    private Long totalEchec;
    private Float pourcentage;
    private Date dateAjout;

    public QuizResult(Long id, String titre, Users users, Quiz quiz, Boolean isUserSucceed, Boolean isUserFailed, Long totalQuestion, Long totalEchec, Float pourcentage, Date dateAjout) {
        this.id = id;
        this.titre = titre;
        this.users = users;
        this.quiz = quiz;
        this.isUserSucceed = isUserSucceed;
        this.isUserFailed = isUserFailed;
        this.totalQuestion = totalQuestion;
        this.totalEchec = totalEchec;
        this.pourcentage = pourcentage;
        this.dateAjout = dateAjout;
    }

    public QuizResult(String titre, Users users, Quiz quiz, Boolean isUserSucceed, Boolean isUserFailed, Long totalQuestion, Long totalEchec, Float pourcentage, Date dateAjout) {
        this.titre = titre;
        this.users = users;
        this.quiz = quiz;
        this.isUserSucceed = isUserSucceed;
        this.isUserFailed = isUserFailed;
        this.totalQuestion = totalQuestion;
        this.totalEchec = totalEchec;
        this.pourcentage = pourcentage;
        this.dateAjout = dateAjout;
    }

    public QuizResult(Long id, String titre, Quiz quiz, Boolean isUserSucceed, Boolean isUserFailed, Long totalQuestion, Long totalEchec, Float pourcentage, Date dateAjout) {
        this.id = id;
        this.titre = titre;
        this.quiz = quiz;
        this.isUserSucceed = isUserSucceed;
        this.isUserFailed = isUserFailed;
        this.totalQuestion = totalQuestion;
        this.totalEchec = totalEchec;
        this.pourcentage = pourcentage;
        this.dateAjout = dateAjout;
    }

    public QuizResult(Long id, String titre, Users users, Boolean isUserSucceed, Boolean isUserFailed, Long totalQuestion, Long totalEchec, Float pourcentage, Date dateAjout) {
        this.id = id;
        this.titre = titre;
        this.users = users;
        this.isUserSucceed = isUserSucceed;
        this.isUserFailed = isUserFailed;
        this.totalQuestion = totalQuestion;
        this.totalEchec = totalEchec;
        this.pourcentage = pourcentage;
        this.dateAjout = dateAjout;
    }
}
