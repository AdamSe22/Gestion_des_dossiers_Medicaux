package ma.adam.dentisteadam.repositories;

import ma.adam.dentisteadam.entities.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUtilisateur extends JpaRepository<Utlisateur,Long> {
    Dentiste findByNomUtlisateur(String nom);
}
