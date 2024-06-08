package ma.adam.dentisteadam.services;

import ma.adam.dentisteadam.entities.*;
import ma.adam.dentisteadam.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Dashboard {
    @Autowired
    IConsultation consultationRepository;
    @Autowired
    IDentiste dentisteRepository;
    @Autowired
    IDossierMedical dossierMedicalRepository;
    @Autowired
    ISituationFinanciere situationFinanciereRepository;
    @Autowired
    ICaisse caisseRepository;
    @Autowired
    IPatient patientRepository;

    public int nombreDeConsultationParDentiste(Dentiste dentiste)
    {
        List<Consultation> consultation=new ArrayList<>();
        List<DossierMedical> dossierMedical=dossierMedicalRepository.findAllByDentiste(dentiste);
        for(int i=0;i<dossierMedical.size();i++)
        {
            consultation=consultationRepository.findByDossierMedical(dossierMedical.get(i));
        }
        return (int)consultation.stream().count();

    }
    public int nombreDeConsultationsParJour(Date date,Dentiste dentiste) {
        List<Consultation> consultation=new ArrayList<>();
        List<DossierMedical> dossierMedical=dossierMedicalRepository.findAllByDentiste(dentiste);
        for(DossierMedical dcx:dossierMedical) {
            consultation = consultationRepository.findByDate(date, dcx);
        }
        return consultation.size();
    }

    public int nombreDeConsultationsParMois(int year, int month, Dentiste dentiste) {
        List<Consultation> consultation=new ArrayList<>();
        List<DossierMedical> dossierMedical=dossierMedicalRepository.findAllByDentiste(dentiste);
        for(DossierMedical dcx:dossierMedical) {
            consultation = consultationRepository.findByYearAndMonth(year, month, dcx);
        }
        return consultation.size();
    }

    public int nombreDeConsultationsParAnnee(int year, Dentiste dentiste) {
        List<Consultation> consultation=new ArrayList<>();
        List<DossierMedical> dossierMedical=dossierMedicalRepository.findAllByDentiste(dentiste);
        for(DossierMedical dcx:dossierMedical) {
           consultation = consultationRepository.findByYear(year, dcx);
        }
        return consultation.size();
    }

    public int nombreDeConsultationsAujourdhui(Dentiste dentiste) {
        Date today = getCurrentDate();
        return nombreDeConsultationsParJour(today, dentiste);
    }

    public int nombreDeConsultationsCeMois(Dentiste dentiste) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return nombreDeConsultationsParMois(year, month, dentiste);
    }
    public double recetteDuJour(Dentiste dentiste)
    {
        List<Caisse> caisses=new ArrayList<>();
        List<SituationFinanciere>situationFinancieres =new ArrayList<>();
        List<DossierMedical> dossierMedicals=dossierMedicalRepository.findAllByDentiste(dentiste);
        for (DossierMedical dossierMedical : dossierMedicals)
            situationFinancieres=situationFinanciereRepository.findAllByDossierMedical(dossierMedical);
        for(SituationFinanciere st:situationFinancieres)
        {
            caisses = caisseRepository.findAllBySituationFinancieres(st);
        }
        return caisses.stream().mapToDouble(c->c.getRecetteDuJours()).sum();


    }
    public double recetteDuMois(Dentiste dentiste)
    {
        List<Caisse> caisses=new ArrayList<>();
        List<SituationFinanciere>situationFinancieres =new ArrayList<>();
        List<DossierMedical> dossierMedicals=dossierMedicalRepository.findAllByDentiste(dentiste);
        for (DossierMedical dossierMedical : dossierMedicals)
            situationFinancieres=situationFinanciereRepository.findAllByDossierMedical(dossierMedical);
        for(SituationFinanciere st:situationFinancieres)
        {
            caisses = caisseRepository.findAllBySituationFinancieres(st);
        }
        return caisses.stream().mapToDouble(c->c.getRecetteDuMois()).sum();


    }
    public double recetteDuAnnee(Dentiste dentiste)
    {
        List<Caisse> caisses=new ArrayList<>();
        List<SituationFinanciere>situationFinancieres =new ArrayList<>();
        List<DossierMedical> dossierMedicals=dossierMedicalRepository.findAllByDentiste(dentiste);
        for (DossierMedical dossierMedical : dossierMedicals)
            situationFinancieres=situationFinanciereRepository.findAllByDossierMedical(dossierMedical);
        for(SituationFinanciere st:situationFinancieres)
        {
            caisses = caisseRepository.findAllBySituationFinancieres(st);
        }
        return caisses.stream().mapToDouble(c->c.getRecetteDuAnnee()).sum();


    }

    public int nombreDeConsultationsCetteAnnee(Dentiste dentiste) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return nombreDeConsultationsParAnnee(year, dentiste);
    }

    public static Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public List<Patient> recentPatient(Dentiste d)
    {
        List<Patient> patients=new ArrayList<>();
        List<DossierMedical> dossierMedical=dossierMedicalRepository.findAllByDentiste(d);
        for(DossierMedical dossierM : dossierMedical)
        {
         patients.add(patientRepository.findByDossierMedical(dossierM));
        }
        List<Patient> lastThreePatients = patients.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        lst -> {
                            Collections.reverse(lst);
                            return lst.stream().limit(3).collect(Collectors.toList());
                        }
                ));


        return lastThreePatients;
    }

}
