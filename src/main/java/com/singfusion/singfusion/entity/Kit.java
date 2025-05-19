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
public class Kit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomdukit;
    private String descrpition;
    private Date date_ajout;

    public Kit(Long id, String nomdukit, String descrpition, Date date_ajout) {
        this.id = id;
        this.nomdukit = nomdukit;
        this.descrpition = descrpition;
        this.date_ajout = date_ajout;
    }

    public Kit(String nomdukit, String descrpition, Date date_ajout) {
        this.nomdukit = nomdukit;
        this.descrpition = descrpition;
        this.date_ajout = date_ajout;
    }


}
