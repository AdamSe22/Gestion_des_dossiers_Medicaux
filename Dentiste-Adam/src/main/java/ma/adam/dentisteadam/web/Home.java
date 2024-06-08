package ma.adam.dentisteadam.web;

import ma.adam.dentisteadam.entities.Dentiste;
import ma.adam.dentisteadam.repositories.IDentiste;
import ma.adam.dentisteadam.repositories.IUtilisateur;
import ma.adam.dentisteadam.services.Dashboard;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @Autowired
    IUtilisateur utilisateurRepository;
    @Autowired
    Dashboard dashboard;
    @GetMapping(path = "/home")
    private String Home()
    {

        return "home";
    }
    @GetMapping(path = "/")
    private String Home2()
    {
        return "redirect:/home";
    }
    @GetMapping(path = "/dashboard")
    private String Dashboard(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Dentiste d = utilisateurRepository.findByNomUtlisateur(authentication.getName());
        System.out.println("************************");
        System.out.println(d);
        int consultationsToday = dashboard.nombreDeConsultationsAujourdhui(d);
        int consultationsThisMonth = dashboard.nombreDeConsultationsCeMois(d);
        int consultationsThisYear = dashboard.nombreDeConsultationsCetteAnnee(d);
        model.addAttribute("consultationsToday", consultationsToday);
        model.addAttribute("consultationsThisMonth", consultationsThisMonth);
        model.addAttribute("consultationsThisYear", consultationsThisYear);
        model.addAttribute("recetteDuJour",dashboard.recetteDuJour(d));
        model.addAttribute("recetteDuMois",dashboard.recetteDuMois(d));
        model.addAttribute("recetteDuAnnee",dashboard.recetteDuAnnee(d));
        model.addAttribute("last_three_patient",dashboard.recentPatient(d));
        System.out.println(dashboard.recentPatient(d));
        return "dashboard";
    }
    
}
