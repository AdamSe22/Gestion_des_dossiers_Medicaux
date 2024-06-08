package ma.adam.dentisteadam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class SituationFinanciere {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private DossierMedical dossierMedical;
    private Double montantGlobalRestant;
    private Double montantGlobalPaye;
    @OneToMany(mappedBy = "situationFinanciere")
    @ToString.Exclude
    private List<Facture>factures;
    @ManyToOne
    @ToString.Exclude
    private Caisse caisse;


}
