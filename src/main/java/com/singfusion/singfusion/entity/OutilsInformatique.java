package com.singfusion.singfusion.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class OutilsInformatique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String descrpition;
    private Date date_ajout;

    public OutilsInformatique(Long id, String titre, String descrpition, Date date_ajout) {
        this.id = id;
        this.titre = titre;
        this.descrpition = descrpition;
        this.date_ajout = date_ajout;
    }

    public OutilsInformatique(String titre, String descrpition, Date date_ajout) {
        this.titre = titre;
        this.descrpition = descrpition;
        this.date_ajout = date_ajout;
    }
}
