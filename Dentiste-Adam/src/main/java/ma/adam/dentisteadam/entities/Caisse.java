package ma.adam.dentisteadam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Caisse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double recetteDuJours;
    private Double recetteDuMois;
    private Double recetteDuAnnee;
    @OneToMany(mappedBy = "caisse")
    @ToString.Exclude
    private Collection<SituationFinanciere> situationFinancieres ;

}
