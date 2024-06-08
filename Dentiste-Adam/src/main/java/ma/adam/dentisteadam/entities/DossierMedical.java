package ma.adam.dentisteadam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;


@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class DossierMedical {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @OneToOne
    @ToString.Exclude
    private Patient patient;
    @OneToOne(mappedBy = "dossierMedical", cascade = CascadeType.ALL, orphanRemoval = true)
    private SituationFinanciere situation_financier;
    private String numeroDossier;
    private String statusPaiement;
    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Consultation> consultations;
    @ManyToOne
    private Dentiste dentiste;

}
