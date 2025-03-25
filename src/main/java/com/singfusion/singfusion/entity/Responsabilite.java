package com.singfusion.singfusion.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Responsabilite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //liaison users_responsabilite
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Users users;
    private String titre;
    private String descrpition;
    private Date date_ajout;

    public Responsabilite(Long id, String titre, Users users, String descrpition, Date date_ajout) {
        this.id = id;
        this.titre = titre;
        this.users = users;
        this.descrpition = descrpition;
        this.date_ajout = date_ajout;
    }

    public Responsabilite(String titre, Users users, String descrpition, Date date_ajout) {
        this.titre = titre;
        this.users = users;
        this.descrpition = descrpition;
        this.date_ajout = date_ajout;
    }

    public Responsabilite(Long id, String titre, String descrpition, Date date_ajout) {
        this.id = id;
        this.titre = titre;
        this.descrpition = descrpition;
        this.date_ajout = date_ajout;
    }
}
