package ma.adam.dentisteadam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class InterventionMedecin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String noteMedcin;
    private Double prixPatient;
    @ManyToOne
    @ToString.Exclude
    private Acte acte;
    @ManyToOne
    @ToString.Exclude
    private Consultation consultation;


}
