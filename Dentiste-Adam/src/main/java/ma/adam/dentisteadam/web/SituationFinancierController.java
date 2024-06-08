package ma.adam.dentisteadam.web;

import jakarta.validation.Valid;
import ma.adam.dentisteadam.entities.Caisse;
import ma.adam.dentisteadam.entities.SituationFinanciere;
import ma.adam.dentisteadam.repositories.ICaisse;
import ma.adam.dentisteadam.repositories.IDossierMedical;
import ma.adam.dentisteadam.repositories.ISituationFinanciere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SituationFinancierController {
    @Autowired
    ICaisse caisseRepository;
    @Autowired
    ISituationFinanciere situationFinanciereRepository;
    @Autowired
    IDossierMedical dossierMedicalRepository;
    @GetMapping(path = "situation_financier")
    private  String situation(Model model, @RequestParam(value = "page",defaultValue = "0") int page,
                              @RequestParam(value = "size",defaultValue = "5") int size)
    {
        var listSituation = situationFinanciereRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("listSituation",listSituation);
        model.addAttribute("current_page",page);
        model.addAttribute("pages",new int[listSituation.getTotalPages()]);
        return "situation_financier";
    }
    @GetMapping(path = "new_situation_financier")
    private String new_situation_financier(Model model)
    {
        model.addAttribute("situation_financier",new SituationFinanciere());
        model.addAttribute("caisse",caisseRepository.findAll());
        model.addAttribute("dossier_medical",dossierMedicalRepository.findAll());
        return "new_situation_financier";

    }
    @PostMapping(path = "save_situation")
    private String save_situation(@Valid SituationFinanciere c, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "new_situation_financier";
        situationFinanciereRepository.save(c);
        return "redirect:/situation_financier";
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping(path = "delete_situation_financier")
    private String delete(@RequestParam(value = "page",defaultValue = "0") int page,Long id)
    {
        jdbcTemplate.execute("DELETE FROM situation_financiere WHERE id="+id);
        return "redirect:/situation_financier?page="+page;
    }
}
