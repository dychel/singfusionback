package com.singfusion.singfusion.entity;
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
    private TypeContenu typeContenu;
    private Date dateAjout;

    public Contenus(Long id, String intutile, TypeContenu typeContenu, Date dateAjout) {
        this.id = id;
        this.intutile = intutile;
        this.typeContenu = typeContenu;
        this.dateAjout = dateAjout;
    }

    public Contenus(String intutile, TypeContenu typeContenu, Date dateAjout) {
        this.intutile = intutile;
        this.typeContenu = typeContenu;
        this.dateAjout = dateAjout;
    }
}
