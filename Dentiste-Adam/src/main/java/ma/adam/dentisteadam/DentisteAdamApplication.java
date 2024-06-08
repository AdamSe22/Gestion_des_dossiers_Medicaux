package ma.adam.dentisteadam;

import ma.adam.dentisteadam.repositories.*;
import ma.adam.dentisteadam.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ma.adam.dentisteadam.enums.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.*;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DentisteAdamApplication implements CommandLineRunner {
    @Autowired
    IPersonne personRepository;
    @Autowired
    IDossierMedical dossierMedicalRepository;
    @Autowired
    ISituationFinanciere situationFinanciereRepository;
    @Autowired
    IFacture factureRepository;
    @Autowired
    IConsultation consultationRepository;
    @Autowired
    IinterventionMedecin interventionMedecinRepository;
    @Autowired
    IActe acteRepository;
    @Autowired
    ICaisse caiseeRepository;
    @Autowired
    IAntecedentMadicale antecedentMadicaleRepository;
    @Autowired
    IUtilisateur iUtilisateur;
    @Autowired
    IPatient patientRepository;

    @Autowired
    IDentiste dentisteRepository;

    public static void main(String[] args)
    {
        SpringApplication.run(DentisteAdamApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager)
//    {
//        PasswordEncoder passwordEncoder=passwordEncoders();
//        return args -> {
//            jdbcUserDetailsManager.createUser(User.withUsername("AdamSe")
//                    .password(passwordEncoder.encode("Ad.1234"))
//                    .roles("DOCTEUR").build());
//
//
//        };
//    }
//    @Bean
//    PasswordEncoder passwordEncoders()
//    {
//        return  new BCryptPasswordEncoder();
//    }

    @Override
    public void run(String... args) throws Exception {

//
//        Utlisateur u = new Utlisateur("Ad.1234@", "AdamSe", Role.ADMIN);
//        u.setAll("Sale", "Serghini", "+212626813767", "Adam", "Serghiniadam4@gmail.com", "Ad.1234@");
//        personRepository.save(u);
//
//        // Create and save patient
//        Patient patient = new Patient(new Date(), Mutuelle.CNSS, GroupSanguin.A_Positif, null, "xxx",null);
//        patient.setAll("Rabat","Serghini","+212600086993","Adam","Adam.Serghini@emsi-edu.ma","AB12341");
//        Patient patient1 = new Patient(new Date(), Mutuelle.CNSS, GroupSanguin.A_Positif, null, "xxx",null);
//        patient1.setAll("Rabat","Serghini","+212600086993","Adam","Adam.Serghini@emsi-edu.ma","AB12341");
//        personRepository.save(patient);
//        personRepository.save(patient1);
//
//        // Create AntecedentMedcin
//        AntecedentMadicale antecedentMadicale=new AntecedentMadicale(null,"Antecedent",CategorieAntecedentMedicaux.MALADIE_CHRONIQUE, Arrays.asList(patient,patient1));
//        AntecedentMadicale antecedentMadicale1=new AntecedentMadicale(null,"Antecedent",CategorieAntecedentMedicaux.MALADIE_CHRONIQUE,Arrays.asList(patient1));
//
//
//        antecedentMadicaleRepository.save(antecedentMadicale);
//        antecedentMadicaleRepository.save(antecedentMadicale1);
//        List<AntecedentMadicale> arr=antecedentMadicaleRepository.findAll();
//        arr.forEach(c->
//                System.out.println(c.getPatients()));
//
//
//        //MANY TO MANY (Add Antecedent to Patient)
////        antecedentMadicale.getPatients().add(patient);
////        Patient x=(Patient)personRepository.getReferenceById(patient.getId());
////        x.getAntecedentMadicales().forEach(System.out::println);
//
//
////        PatientAntecedent patientAntecedent=new PatientAntecedent();
////        patientAntecedent.addPatientToAntecedent(patient,antecedentMadicale);
////        Patient pp=(Patient) personRepository.getReferenceById(patient.getId());
////        pp.getAntecedentMadicales().forEach(System.out::println);
//
//        //FROM UTILLISATEUR TESTER DENTISTE?
//        Utlisateur dentiste=new Dentiste(new Date(),20000D,Specialite.ENDODONTiE,Assurence.CIMR,Disponibilite.AUTRE,StatusEmploye.AUTRE,null);
//        dentiste.setRole(Role.ADMIN);
//        dentiste.setMotDePasse("AD.1234@");
//        dentiste.setNomUtlisateur("SERAPHIM");
//        iUtilisateur.save(dentiste);
//
//        // Create and save dossier medical
//        DossierMedical dossierMedical = new DossierMedical(null, new Date(), patient, null, "Adam", "Az_420", "check",null,(Dentiste) dentiste);
//        dossierMedicalRepository.save(dossierMedical);
//
//
//        //Create Caisse
//        Caisse caisse=new Caisse(null,200D,200D,100D,null);
//        caiseeRepository.save(caisse);
//
//
//        // Create and save situation financiere
//        SituationFinanciere situationFinanciere=new SituationFinanciere(null,dossierMedical,1000D,200D,null,caisse);
//        situationFinanciereRepository.save(situationFinanciere);
//
//
//        // Create Consultation
//        Consultation consultation=new Consultation(null,dossierMedical,null,new Date(), TypeConsultation.URGENCE,null);
//        Consultation consultation1=new Consultation(null,dossierMedical,null,new Date(), TypeConsultation.URGENCE,null);
//
//        consultationRepository.save(consultation);
//        consultationRepository.save(consultation1);
//
//
//        // Create Facture
//        Facture facture=new Facture(null,situationFinanciere,200D,100D,new Date(),10000D,consultation, TypePaiement.AUTRE);
//        Facture facture2=new Facture(null,situationFinanciere,813D,7230D,new Date(),1800D,consultation, TypePaiement.CHEQUE);
//        factureRepository.save(facture);
//        factureRepository.save(facture2);
//
//
//        // Create Acte
//        Acte acte=new Acte(null,200D,CategorieActe.ORTHODONISTE,"libelle",null);
//        acteRepository.save(acte);
//
//
//
//        //Create InterventionMedecin
//        InterventionMedecin interventionMedecin=new InterventionMedecin(null,"good",2000D,12L,acte,consultation);
//        interventionMedecinRepository.save(interventionMedecin);
//
//
//        // Fetch all persons and print
//        List<Personne> people = personRepository.findAll();
//        people.forEach(System.out::println);
//        //Fetch all situation
//        SituationFinanciere situationFinanciere1=situationFinanciereRepository.getReferenceById(1L);
//        factureRepository.findBySituationFinanciere(situationFinanciere1).forEach(System.out::println);
//        //searching all consultation in Dossier Medical
//        System.out.println("---------------------->");
//        consultationRepository.findByDossierMedical(dossierMedical).forEach(System.out::println);
//        //searching all caisse in intervention medical
//        System.out.println("Caisse ----------------------> Situation financier");
//        caiseeRepository.findAllBySituationFinancieres(situationFinanciere1).forEach(System.out::println);
//
//
//
//        iUtilisateur.findAll().forEach(System.out::println);
//        patientRepository.findAll().forEach(System.out::println);
//        antecedentMadicaleRepository.findAll().forEach(System.out::println);
//        dentisteRepository.findAll().forEach(c->
//        {
//            System.out.println(c.getId());
//        });
//        Dentiste d = dentisteRepository.findById(4L).orElse(null);
//        System.out.println(d);
        Dentiste dx = iUtilisateur.findByNomUtlisateur("SERAPHIM");
        System.out.println(dx);



    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        for (int i=0;i<50;i++)
//
//        patientRepository.save(new Patient(null,"adam","serghini",new Date(),(int)Math.random()*100));
//        patientService.mesPatients().forEach(c->
//        {
//            System.out.println("--------------->"+c);
//        });
//         Page<Patient> all=patientRepository.findAll(PageRequest.of(0,5));
//        System.out.println(all.getTotalPages());
//        System.out.println(all.getTotalElements());
//        System.out.println(all.getNumber());
//        List<Patient>content=all.getContent();
//        List<Patient> pat=patientRepository.findByPrenom("serghini");
//        pat.forEach(c->
//        {
//            System.out.println(c.getNom()+" "+c.getPrenom());
//        });
//        content.forEach(c->
//        {
//            System.out.println("===================================>");
//            System.out.println(c.getId());
//            System.out.println(c.getNom());
//            System.out.println(c.getPrenom());
//            System.out.println(c.getDateNaissance());
//        });
//        Patient patient =patientRepository.findById(1L).orElse(null);
//        if(patient!=null)
//            System.out.println("not found ");
//        else
//            System.out.println("exist");
//
//    }
    }

