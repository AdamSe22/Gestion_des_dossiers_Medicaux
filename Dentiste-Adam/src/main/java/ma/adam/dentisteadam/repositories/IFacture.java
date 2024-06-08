package ma.adam.dentisteadam.repositories;

import ma.adam.dentisteadam.entities.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFacture extends JpaRepository<Facture,Long> {
    List<Facture> findBySituationFinanciere(SituationFinanciere situationFinanciere);
    Page<Facture> findAll(Pageable pageable);

}
