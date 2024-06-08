package ma.adam.dentisteadam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.adam.dentisteadam.entities.*;
import ma.adam.dentisteadam.enums.*;

import java.util.Date;

@Entity
@AllArgsConstructor @NoArgsConstructor
@DiscriminatorValue(value = "SEC")
public class Secretaire extends Utlisateur{
    private Double Salaire;
    @Temporal(TemporalType.DATE)
    private Date dateRetourConge;
    @Enumerated(EnumType.STRING)
    private Assurence assurence;
    @Enumerated(EnumType.STRING)
    private StatusEmploye statusActuel;
    private Double prime;
}
