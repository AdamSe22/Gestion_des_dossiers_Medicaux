package ma.adam.dentisteadam.repositories;

import ma.adam.dentisteadam.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActe extends JpaRepository<Acte,Long> {
    Page<Acte> findAll(Pageable pageable);
}
