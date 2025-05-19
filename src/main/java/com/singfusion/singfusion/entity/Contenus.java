package com.singfusion.singfusion.entity;
import com.singfusion.singfusion.config.Etapes;
import com.singfusion.singfusion.config.TypeContenu;
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
public class Contenus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String intutile;
    private String typeContenu;
    private Date dateAjout;
    private String etapes;
    private Long numeroEtape;
    private String lien;

    public Contenus(Long id, String intutile, String typeContenu, Date dateAjout, String etapes, Long numeroEtape, String lien) {
        this.id = id;
        this.intutile = intutile;
        this.typeContenu = typeContenu;
        this.dateAjout = dateAjout;
        this.etapes = etapes;
        this.numeroEtape = numeroEtape;
        this.lien = lien;
    }

    public Contenus(String intutile, String typeContenu, Date dateAjout, String etapes, Long numeroEtape, String lien) {
        this.intutile = intutile;
        this.typeContenu = typeContenu;
        this.dateAjout = dateAjout;
        this.etapes = etapes;
        this.numeroEtape = numeroEtape;
        this.lien = lien;
    }

}
