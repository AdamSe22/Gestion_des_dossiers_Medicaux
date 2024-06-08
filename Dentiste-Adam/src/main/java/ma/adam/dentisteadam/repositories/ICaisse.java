package ma.adam.dentisteadam.repositories;

import ma.adam.dentisteadam.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ICaisse extends JpaRepository<Caisse,Long> {
    List <Caisse> findAllBySituationFinancieres(SituationFinanciere situationFinanciere);
    Page<Caisse> findAll(Pageable pageable);
}
