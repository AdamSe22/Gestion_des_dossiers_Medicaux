package ma.adam.dentisteadam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.adam.dentisteadam.entities.*;
import ma.adam.dentisteadam.enums.*;

@Entity @DiscriminatorValue("UTI")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",length = 3,discriminatorType=DiscriminatorType.STRING)
public class Utlisateur extends Personne{
    private String motDePasse;
    private String nomUtlisateur;
    @Enumerated(EnumType.STRING)
    private Role role;
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
