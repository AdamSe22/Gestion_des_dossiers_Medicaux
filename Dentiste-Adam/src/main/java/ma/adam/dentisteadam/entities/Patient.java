package ma.adam.dentisteadam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.adam.dentisteadam.entities.*;
import ma.adam.dentisteadam.enums.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("PAT")
@Data @AllArgsConstructor@NoArgsConstructor
public class Patient extends Personne{
     @Temporal(TemporalType.DATE)
     @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
     @Enumerated(EnumType.STRING)
     private Mutuelle mutuelle;
     @Enumerated(EnumType.STRING)
    private GroupSanguin groupSanguin;
    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private DossierMedical dossierMedical;
    private String profession;
    @ManyToMany(mappedBy = "patients",fetch = FetchType.EAGER)
    List<AntecedentMadicale> antecedentMadicales=new ArrayList<>();



    public void setAll(
            String adresse,
            String nom,
            String telephone,
            String prenom,
            String email,
            String cin)
    {
        setAdresse(adresse);setNom(nom);setTelephone(telephone);setPrenom(prenom);setEmail(email);setCin(cin);
    }

}
