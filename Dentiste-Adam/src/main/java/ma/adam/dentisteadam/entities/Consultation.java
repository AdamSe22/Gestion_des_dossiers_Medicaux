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
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Consultation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @ManyToOne
    @ToString.Exclude
    private DossierMedical dossierMedical;
    @OneToMany(mappedBy = "consultation")
    @ToString.Exclude
    private List<InterventionMedecin> interventionMedecins ;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateConsultation;
    @Enumerated(EnumType.STRING)
    private TypeConsultation typeConsultation;
    @OneToMany(mappedBy = "consultation")
    @ToString.Exclude
    private List<Facture> factureList;
}
