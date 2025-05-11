package com.singfusion.singfusion.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Formalites {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users users;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "formalite_acces",
            joinColumns = {
                    @JoinColumn(name = "formalite_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "acces_id", referencedColumnName = "id")
            }
    )
    private List<Acces> acces;
    @ManyToOne
    @JoinColumn(name = "responsabilite_id", referencedColumnName = "id")
    private Responsabilite responsabilite;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "formalite_outils",
            joinColumns = {
                    @JoinColumn(name = "formalite_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "outils_id", referencedColumnName = "id")
            }
    )
    private List<OutilsInformatique> outilsInformatique;
    @ManyToOne
    @JoinColumn(name = "postetravail_id", referencedColumnName = "id")
    private Postetravail postetravail;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "formalite_kit",
            joinColumns = {
                    @JoinColumn(name = "formalite_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "kit_id", referencedColumnName = "id")
            }
    )
    private List<Kit> kit;
    private String titre;
    private String descritpion;
    private Date date_ajout;
    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;
    private Boolean AsKit=false;
    private Boolean AsAccess=false;
    private Boolean AsOutilsInfo=false;
    private Boolean AsPoste=false;
    private Boolean AsVisited=false;
    private Boolean IsFinished=false;
}
