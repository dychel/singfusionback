package com.singfusion.singfusion.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String question;
    private String reponses;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users poserPar;
    private Long reponduPar;
    private Date date;

    public Faq(Long id, String titre, String question, String reponses, Users poserPar, Long reponduPar, Date date) {
        this.id = id;
        this.titre = titre;
        this.question = question;
        this.reponses = reponses;
        this.poserPar = poserPar;
        this.reponduPar = reponduPar;
        this.date = date;
    }

    public Faq(String titre, String question, String reponses, Users poserPar, Long reponduPar, Date date) {
        this.titre = titre;
        this.question = question;
        this.reponses = reponses;
        this.poserPar = poserPar;
        this.reponduPar = reponduPar;
        this.date = date;
    }

    public Faq(Long id, String titre, String question, String reponses, Long reponduPar, Date date) {
        this.id = id;
        this.titre = titre;
        this.question = question;
        this.reponses = reponses;
        this.reponduPar = reponduPar;
        this.date = date;
    }

}
