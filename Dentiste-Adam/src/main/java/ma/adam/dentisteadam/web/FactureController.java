package ma.adam.dentisteadam.web;

import jakarta.validation.Valid;
import ma.adam.dentisteadam.entities.Consultation;
import ma.adam.dentisteadam.entities.Facture;
import ma.adam.dentisteadam.enums.TypeConsultation;
import ma.adam.dentisteadam.enums.TypePaiement;
import ma.adam.dentisteadam.repositories.IConsultation;
import ma.adam.dentisteadam.repositories.IFacture;
import ma.adam.dentisteadam.repositories.ISituationFinanciere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FactureController {
    @Autowired
    IFacture factureRepository ;
    @Autowired
    IConsultation consultationRepository;
    @Autowired
    ISituationFinanciere situationFinanciereRepository;
    @GetMapping(path = "facture")
    private String factures(@RequestParam(value = "page",defaultValue = "0")int page,
                            @RequestParam(value = "size",defaultValue = "5")int size,
                            Model model)
    {
        var listFacture=factureRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("listFacture",listFacture);
        model.addAttribute("current_page",page);
        model.addAttribute("pages",new int[listFacture.getTotalPages()]);
        model.addAttribute("autre", TypePaiement.AUTRE);
        return "facture";
    }

    @GetMapping(path="new_facture")
    private String new_facture(Model model,@RequestParam(value = "page",defaultValue = "0")int page)
    {
        model.addAttribute("current_page",page);
        model.addAttribute("facture",new Facture());
        model.addAttribute("consultation", consultationRepository.findAll());
        model.addAttribute("situation_financier",situationFinanciereRepository.findAll());
        model.addAttribute("type_payement",TypePaiement.values());
        return "new_facture";
    }
    @PostMapping(path = "save_facture")
    private String save_facture(@Valid Facture f, BindingResult bindingResult, @RequestParam(value = "page" , defaultValue = "0") int page)
    {
        if(bindingResult.hasErrors())
            return "new_consultation";
        factureRepository.save(f);
        return "redirect:/facture?page="+page;

    }
    @GetMapping(path = "deleteFacture")
    private String delete(@RequestParam(value = "page",defaultValue = "0")int page,Long id)
    {
        factureRepository.deleteById(id);
        return "redirect:/facture?page="+page;

    }
    @GetMapping(path = "edit_facture")
    private String editFacture(@RequestParam(value = "page",defaultValue = "0")int page,Long id,Model model)
    {
        Facture facture=factureRepository.findById(id).orElse(null);
        System.out.println("********************************0"+facture);
        model.addAttribute("facture",facture);
        model.addAttribute("current_page",page);
        model.addAttribute("consultation", consultationRepository.findAll());
        model.addAttribute("situation_financier",situationFinanciereRepository.findAll());
        model.addAttribute("type_payement",TypePaiement.values());
        return "edit_facture";
    }
}
