package ma.adam.dentisteadam.repositories;

import ma.adam.dentisteadam.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface IDossierMedical extends JpaRepository<DossierMedical,Long> {
    List<DossierMedical> findAllByDentiste(Dentiste dentiste);

    Page<DossierMedical> findAllByStatusPaiementContains(String status,Pageable pageable);
}
