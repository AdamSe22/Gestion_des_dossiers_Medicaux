package ma.adam.dentisteadam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.adam.dentisteadam.entities.*;
import ma.adam.dentisteadam.enums.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AntecedentMadicale {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @Enumerated(EnumType.STRING)
    private CategorieAntecedentMedicaux categorieAntecedentMedicaux;
    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Patient> patients=new ArrayList<>();
}
