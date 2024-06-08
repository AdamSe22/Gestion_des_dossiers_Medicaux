package ma.adam.dentisteadam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.adam.dentisteadam.entities.*;
import ma.adam.dentisteadam.enums.*;


import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Acte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double prixDeBase ;
    @Enumerated(EnumType.STRING)
    private CategorieActe categorieActe;
    private String libelle;
    @OneToMany (mappedBy = "acte")
    @ToString.Exclude
    private List<InterventionMedecin> interventionMedecins;

}
