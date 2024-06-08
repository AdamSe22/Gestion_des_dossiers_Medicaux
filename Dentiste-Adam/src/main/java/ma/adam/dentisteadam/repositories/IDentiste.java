package ma.adam.dentisteadam.repositories;

import ma.adam.dentisteadam.entities.Dentiste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDentiste extends JpaRepository<Dentiste,Long> {

}
