package com.singfusion.singfusion.entity;
import com.singfusion.singfusion.config.Etapes;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.File;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String intitule;
    private String format;
    private File file;
    private Date dateAjout;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;
    private Etapes etapes;

    public Document(Long id, String titre, String intitule, String format, File file, Date dateAjout, Users users, Etapes etapes) {
        this.id = id;
        this.titre = titre;
        this.intitule = intitule;
        this.format = format;
        this.file = file;
        this.dateAjout = dateAjout;
        this.users = users;
        this.etapes = etapes;
    }

    public Document(String titre, String intitule, String format, File file, Date dateAjout, Users users, Etapes etapes) {
        this.titre = titre;
        this.intitule = intitule;
        this.format = format;
        this.file = file;
        this.dateAjout = dateAjout;
        this.users = users;
        this.etapes = etapes;
    }

    public Document(Long id, String titre, String intitule, String format, File file, Date dateAjout, Etapes etapes) {
        this.id = id;
        this.titre = titre;
        this.intitule = intitule;
        this.format = format;
        this.file = file;
        this.dateAjout = dateAjout;
        this.etapes = etapes;
    }
}
