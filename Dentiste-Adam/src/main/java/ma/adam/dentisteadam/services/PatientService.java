package ma.adam.dentisteadam.services;

import ma.adam.dentisteadam.entities.Patient;
import ma.adam.dentisteadam.repositories.IPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class PatientService {
    @Autowired
    IPatient patientRepository;
    public Page<Patient> getPatient(String nom,int page,int size)
    {
        return patientRepository.findByNomContains(nom, PageRequest.of(page,size));
    }
}
