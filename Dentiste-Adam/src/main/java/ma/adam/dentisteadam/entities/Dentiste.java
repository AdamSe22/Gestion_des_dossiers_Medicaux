package ma.adam.dentisteadam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.adam.dentisteadam.entities.*;
import ma.adam.dentisteadam.enums.*;

import java.util.Date;
import  java.util.*;
@Entity
@NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue(value = "DEN")
public class Dentiste extends Utlisateur{
    @Temporal(TemporalType.DATE)
    private Date dateRetourConge;
    private Double salaireDeBase;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    @Enumerated(EnumType.STRING)
    private Assurence assurence;
    @Enumerated(EnumType.STRING)
    private Disponibilite disponibilite;
    @Enumerated(EnumType.STRING)
    private StatusEmploye statusActuel;
    @OneToMany(mappedBy = "dentiste")
    private List<DossierMedical> dossierMedicals;
}
