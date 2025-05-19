package com.singfusion.singfusion.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class ConnaissanceDonnee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;
    private String objectif;
    private String contexte;
    private String methodologieConsultation;
    private String conseil;
    @OneToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;
    private Date dateAjout;
    private Boolean IsFinished=false;

    public ConnaissanceDonnee(Long id, String titre, String description, Document document, Users users, String objectif, String contexte, String methodologieConsultation, String conseil, Quiz quiz, Date dateAjout, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.document = document;
        this.users = users;
        this.objectif = objectif;
        this.contexte = contexte;
        this.methodologieConsultation = methodologieConsultation;
        this.conseil = conseil;
        this.quiz = quiz;
        this.dateAjout = dateAjout;
        IsFinished = isFinished;
    }

    public ConnaissanceDonnee(String titre, String description, Document document, Users users, String objectif, String contexte, String methodologieConsultation, String conseil, Quiz quiz, Date dateAjout, Boolean isFinished) {
        this.titre = titre;
        this.description = description;
        this.document = document;
        this.users = users;
        this.objectif = objectif;
        this.contexte = contexte;
        this.methodologieConsultation = methodologieConsultation;
        this.conseil = conseil;
        this.quiz = quiz;
        this.dateAjout = dateAjout;
        IsFinished = isFinished;
    }

    public ConnaissanceDonnee(Long id, String titre, String description, Users users, String objectif, String contexte, String methodologieConsultation, String conseil, Quiz quiz, Date dateAjout, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.users = users;
        this.objectif = objectif;
        this.contexte = contexte;
        this.methodologieConsultation = methodologieConsultation;
        this.conseil = conseil;
        this.quiz = quiz;
        this.dateAjout = dateAjout;
        IsFinished = isFinished;
    }

    public ConnaissanceDonnee(Long id, String titre, String description, Document document, String objectif, String contexte, String methodologieConsultation, String conseil, Quiz quiz, Date dateAjout, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.document = document;
        this.objectif = objectif;
        this.contexte = contexte;
        this.methodologieConsultation = methodologieConsultation;
        this.conseil = conseil;
        this.quiz = quiz;
        this.dateAjout = dateAjout;
        IsFinished = isFinished;
    }

    public ConnaissanceDonnee(Long id, String titre, String description, Document document, Users users, String objectif, String contexte, String methodologieConsultation, String conseil, Date dateAjout, Boolean isFinished) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.document = document;
        this.users = users;
        this.objectif = objectif;
        this.contexte = contexte;
        this.methodologieConsultation = methodologieConsultation;
        this.conseil = conseil;
        this.dateAjout = dateAjout;
        IsFinished = isFinished;
    }
}
