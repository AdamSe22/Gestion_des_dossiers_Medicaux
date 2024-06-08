package ma.adam.dentisteadam.web;

import jakarta.validation.Valid;
import ma.adam.dentisteadam.entities.Dentiste;
import ma.adam.dentisteadam.entities.DossierMedical;
import ma.adam.dentisteadam.enums.StatusEmploye;
import ma.adam.dentisteadam.enums.TypePaiement;
import ma.adam.dentisteadam.repositories.IDossierMedical;
import ma.adam.dentisteadam.repositories.IPatient;
import ma.adam.dentisteadam.repositories.IUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

@Controller
public class DossierMedicalController {
    @Autowired
    IDossierMedical dossierMedicalRepository;
    @Autowired
    IPatient patientRepository;
    @Autowired
    IUtilisateur utilisateurRepository;
    @GetMapping("/dossier_medical")
    private String dossier_medical(@RequestParam(value = "page",defaultValue = "0")int page,
                                   @RequestParam(value = "size",defaultValue = "5")int size,
                                   @RequestParam(value = "status",defaultValue = "")String status,
                                   Model model)
    {
        var listDossierMedical=dossierMedicalRepository.findAllByStatusPaiementContains(status, PageRequest.of(page,size));
        model.addAttribute("listDossierMedical",listDossierMedical);
        model.addAttribute("current_page",page);
        model.addAttribute("status",status);
        model.addAttribute("pages",new int[listDossierMedical.getTotalPages()]);
        return "dossier_medical";

    }
    @GetMapping(path = "/new_dossier_medical")
    private String newDossierMedical(Model model)
    {
        DossierMedical dossierMedical=new DossierMedical();
        model.addAttribute("status", TypePaiement.values());
        model.addAttribute("dossier_medical",dossierMedical);
        model.addAttribute("patient",patientRepository.findAll());
        return "new_dossier_medical";

    }

    @PostMapping(path = "/saves")
    public String save(@Valid DossierMedical p, BindingResult bindingResult, @RequestParam(value = "page" , defaultValue = "0") int page,
                       @RequestParam(value = "status",defaultValue = "") String status)
    {
        if(bindingResult.hasErrors())
            return "new_dossier_medical";
        System.out.println("****************************************************************");
        p.setDateCreation(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Dentiste d = utilisateurRepository.findByNomUtlisateur(authentication.getName());
        p.setDentiste(d);
        dossierMedicalRepository.save(p);
        return "redirect:/dossier_medical?status="+status+"&page="+page;

    }
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping(path = "/delete_dossier_medical")
    private  String delete(Long id,@RequestParam(value = "page")int page,@RequestParam(value = "status",defaultValue = "") String status)
    {
        DossierMedical d = dossierMedicalRepository.findById(id).orElse(null);
        System.out.println("***************************************************************"+d);
        dossierMedicalRepository.deleteById(id);
        jdbcTemplate.execute("DELETE FROM dossier_medical where id = "+id);
        return "redirect:/dossier_medical?status="+status+"&page="+page;

    }
    @GetMapping(path = "/edit_dossier_medical")
    private String edit_dossier_medical(Long id,Model model,@RequestParam(value = "page")int page,@RequestParam(value = "status",defaultValue = "") String status)
    {
        DossierMedical d = dossierMedicalRepository.findById(id).orElse(null);
        DossierMedical dossierMedical=dossierMedicalRepository.findById(id).orElse(null);
        System.out.println(dossierMedical.getStatusPaiement()+"///////////////////////");
        List<String> typePaiementStrings = Arrays.stream(TypePaiement.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        model.addAttribute("statu",typePaiementStrings);
        model.addAttribute("dossier_medical",dossierMedical);
        model.addAttribute("current_page",page);
        model.addAttribute("patient",patientRepository.findAll());
        return "edit_dossier_medical";

    }

    @PostMapping(path = "/edits")
    public String edits(@Valid DossierMedical p, BindingResult bindingResult, @RequestParam(value = "page" , defaultValue = "0") int page,
                       @RequestParam(value = "status",defaultValue = "") String status)
    {
        jdbcTemplate.execute("UPDATE dossier_medical set numero_dossier='"+p.getNumeroDossier()+"',status_paiement='"+p.getStatusPaiement()+"'where id="+ p.getId());
        return "redirect:/dossier_medical?status="+status+"&page="+page;

    }


}
