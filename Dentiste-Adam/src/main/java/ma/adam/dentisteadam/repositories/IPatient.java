package ma.adam.dentisteadam.repositories;

import ma.adam.dentisteadam.entities.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatient extends JpaRepository<Patient,Long> {
    Patient findByDossierMedical(DossierMedical dossierMedical);
    Page<Patient> findByNomContains(String name, Pageable pageable);
}
