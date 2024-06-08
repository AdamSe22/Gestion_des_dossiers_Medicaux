package ma.adam.dentisteadam.repositories;

import ma.adam.dentisteadam.entities.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IConsultation extends JpaRepository<Consultation,Long> {
    List<Consultation> findByDossierMedical(DossierMedical dossierMedical);
    Page<Consultation> findAll(Pageable pageable);

    @Query("SELECT c FROM Consultation c WHERE c.dateConsultation = :date AND c.dossierMedical = :dossierMedical")
    List<Consultation> findByDate(@Param("date") Date date, @Param("dossierMedical") DossierMedical dossierMedical);

    @Query("SELECT c FROM Consultation c WHERE FUNCTION('YEAR', c.dateConsultation) = :year AND FUNCTION('MONTH', c.dateConsultation) = :month AND c.dossierMedical = :dossierMedical")
    List<Consultation> findByYearAndMonth(@Param("year") int year, @Param("month") int month, @Param("dossierMedical") DossierMedical dossierMedical);

    @Query("SELECT c FROM Consultation c WHERE FUNCTION('YEAR', c.dateConsultation) = :year AND c.dossierMedical = :dossierMedical")
    List<Consultation> findByYear(@Param("year") int year, @Param("dossierMedical") DossierMedical dossierMedical);

}
