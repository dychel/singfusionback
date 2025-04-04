package com.singfusion.singfusion.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String intitule;
    private String description;
    private Date datedebut;
    private Date datefin;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;


    public Projet(Long id, String intitule, String description, Date datedebut, Date datefin, Users users, Document document) {
        this.id = id;
        this.intitule = intitule;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.users = users;
        this.document = document;
    }

    public Projet(String intitule, String description, Date datedebut, Date datefin, Users users, Document document) {
        this.intitule = intitule;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.users = users;
        this.document = document;
    }

    public Projet(Long id, String intitule, String description, Date datedebut, Date datefin, Document document) {
        this.id = id;
        this.intitule = intitule;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.document = document;
    }

    public Projet(Long id, String intitule, String description, Date datedebut, Date datefin, Users users) {
        this.id = id;
        this.intitule = intitule;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.users = users;
    }
}
