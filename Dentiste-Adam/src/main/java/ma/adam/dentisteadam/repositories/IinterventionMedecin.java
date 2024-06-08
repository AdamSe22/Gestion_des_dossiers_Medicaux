package ma.adam.dentisteadam.repositories;

import ma.adam.dentisteadam.entities.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IinterventionMedecin extends JpaRepository<InterventionMedecin,Long> {
    List<InterventionMedecin> findAllByConsultation(Consultation consultation);
    List<InterventionMedecin> findAllByActe(Acte acte);
    Page<InterventionMedecin> findAll(Pageable pageable);
}
