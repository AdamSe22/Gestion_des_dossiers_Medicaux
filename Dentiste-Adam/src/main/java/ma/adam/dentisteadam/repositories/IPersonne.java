package ma.adam.dentisteadam.repositories;

import ma.adam.dentisteadam.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonne extends JpaRepository<Personne,Long> {
}
