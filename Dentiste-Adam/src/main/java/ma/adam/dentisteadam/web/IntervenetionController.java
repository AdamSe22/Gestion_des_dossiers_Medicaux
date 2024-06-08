package ma.adam.dentisteadam.web;

import jakarta.validation.Valid;
import ma.adam.dentisteadam.entities.Caisse;
import ma.adam.dentisteadam.entities.Facture;
import ma.adam.dentisteadam.entities.InterventionMedecin;
import ma.adam.dentisteadam.enums.TypePaiement;
import ma.adam.dentisteadam.repositories.IActe;
import ma.adam.dentisteadam.repositories.IConsultation;
import ma.adam.dentisteadam.repositories.IinterventionMedecin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IntervenetionController {
    @Autowired
    IinterventionMedecin interventionMedecinRepository;
    @Autowired
    IActe acteRepository;
    @Autowired
    IConsultation consultationRepository;

    @GetMapping(path = "intervention")
    private String interventions(@RequestParam(value = "page",defaultValue = "0")int page,
                                 @RequestParam(value = "size",defaultValue = "5")int size,
                                 Model model)
    {
        var listIntervenetion=interventionMedecinRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("listFacture",listIntervenetion);
        model.addAttribute("current_page",page);
        model.addAttribute("pages",new int[listIntervenetion.getTotalPages()]);
        return "intervention";

    }
    @GetMapping(path = "deleteIntervention")
    private String deleteActe(@RequestParam(value = "page" , defaultValue = "0") int page,Long id)
    {
        interventionMedecinRepository.deleteById(id);
        return "redirect:/intervention?page="+page;

    }
    @GetMapping(path = "new_intervention")
    private String new_intervention(Model model, @RequestParam(value = "page",defaultValue = "0") int page,
                              @RequestParam(value = "size",defaultValue = "5") int size)
    {
        model.addAttribute("intervention",new InterventionMedecin());
        model.addAttribute("acte",acteRepository.findAll());
        model.addAttribute("consultation",consultationRepository.findAll());
        model.addAttribute("current_page",page);
        return "new_intervention";
    }
    @PostMapping(path = "save_intervention")
    private String save_intervention(@Valid InterventionMedecin i, BindingResult bindingResult, @RequestParam(value = "page" , defaultValue = "0") int page)
    {
        if(bindingResult.hasErrors())
            return "new_intervention";
        interventionMedecinRepository.save(i);
        return "redirect:/intervention?page="+page;
    }
    @GetMapping(path = "edit_intervention")
    private String editFacture(@RequestParam(value = "page",defaultValue = "0")int page,Long id,Model model)
    {
        InterventionMedecin interventionMedecin=interventionMedecinRepository.findById(id).orElse(null);
        model.addAttribute("intervention",interventionMedecin);
        model.addAttribute("acte",acteRepository.findAll());
        model.addAttribute("consultation",consultationRepository.findAll());
        model.addAttribute("current_page",page);

        return "edit_intervention";
    }
}
