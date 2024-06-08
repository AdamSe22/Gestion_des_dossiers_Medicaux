package ma.adam.dentisteadam.repositories;

import ma.adam.dentisteadam.entities.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface ISituationFinanciere extends JpaRepository<SituationFinanciere,Long> {
    List<SituationFinanciere> findAllByDossierMedical(DossierMedical dossierMedical);
    Page<SituationFinanciere> findAll(Pageable pageable);

}
