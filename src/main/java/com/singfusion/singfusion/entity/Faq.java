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
    private Users users;
    private Date date;
    private String categorie;

    public Faq(Long id, String titre, String question, String reponses, Users users, Date date, String categorie) {
        this.id = id;
        this.titre = titre;
        this.question = question;
        this.reponses = reponses;
        this.users = users;
        this.date = date;
        this.categorie = categorie;
    }

    public Faq(String titre, String question, String reponses, Users users, Date date, String categorie) {
        this.titre = titre;
        this.question = question;
        this.reponses = reponses;
        this.users = users;
        this.date = date;
        this.categorie = categorie;
    }

    public Faq(Long id, String titre, String question, String reponses, Date date, String categorie) {
        this.id = id;
        this.titre = titre;
        this.question = question;
        this.reponses = reponses;
        this.date = date;
        this.categorie = categorie;
    }

}
