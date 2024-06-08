package ma.adam.dentisteadam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.adam.dentisteadam.entities.*;
import ma.adam.dentisteadam.enums.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Facture {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @ToString.Exclude
    private SituationFinanciere situationFinanciere;
    private Double montantRestant;
    private Double montantPaye;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFacturation;
    private Double montantTotal;
    @ManyToOne
    @ToString.Exclude
    private Consultation consultation;
    @Enumerated(EnumType.STRING)
    private TypePaiement typePaiement;

}
