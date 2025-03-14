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
public class responsable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Users users;
    private String descrpition;
    private Date date_ajout;

    public responsable(Long id, Users users, String descrpition, Date date_ajout) {
        this.id = id;
        this.users = users;
        this.descrpition = descrpition;
        this.date_ajout = date_ajout;
    }

    public responsable(Users users, String descrpition, Date date_ajout) {
        this.users = users;
        this.descrpition = descrpition;
        this.date_ajout = date_ajout;
    }

    public responsable(Long id, String descrpition, Date date_ajout) {
        this.id = id;
        this.descrpition = descrpition;
        this.date_ajout = date_ajout;
    }
}
