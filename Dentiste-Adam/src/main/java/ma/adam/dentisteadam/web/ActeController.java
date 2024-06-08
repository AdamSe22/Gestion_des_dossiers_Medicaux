package ma.adam.dentisteadam.web;

import jakarta.validation.Valid;
import ma.adam.dentisteadam.entities.Acte;
import ma.adam.dentisteadam.entities.Caisse;
import ma.adam.dentisteadam.entities.InterventionMedecin;
import ma.adam.dentisteadam.enums.CategorieActe;
import ma.adam.dentisteadam.repositories.IActe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActeController {
    @Autowired
    IActe acteRepository;
    @GetMapping(path = "acte")
    private String actes(@RequestParam(value = "page",defaultValue = "0")int page,
                         @RequestParam(value = "size",defaultValue = "5")int size,
                         Model model)
    {
        var listActe=acteRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("listActe",listActe);
        model.addAttribute("current_page",page);
        model.addAttribute("category_acte", CategorieActe.values());
        model.addAttribute("pages",new int[listActe.getTotalPages()]);
        return "acte";

    }
    @GetMapping(path = "deleteActe")
    private String deleteActe(@RequestParam(value = "page" , defaultValue = "0") int page,Long id)
    {
        acteRepository.deleteById(id);
        return "redirect:/acte?page="+page;

    }
    @GetMapping(path = "new_acte")
    private String new_acte(Model model, @RequestParam(value = "page",defaultValue = "0") int page,
                              @RequestParam(value = "size",defaultValue = "5") int size)
    {
        model.addAttribute("acte",new Acte());
        model.addAttribute("current_page",page);
        model.addAttribute("category",CategorieActe.values());
        return "new_acte";
    }
    @PostMapping(path = "save_acte")
    private String save(@Valid Acte a, BindingResult bindingResult, @RequestParam(value = "page" , defaultValue = "0") int page)
    {
        if(bindingResult.hasErrors())
            return "new_acte";
        acteRepository.save(a);
        return "redirect:/acte?page="+page;
    }
    @GetMapping(path = "edit_acte")
    private String editFacture(@RequestParam(value = "page",defaultValue = "0")int page,Long id,Model model)
    {
        Acte acte=acteRepository.findById(id).orElse(null);
        model.addAttribute("acte",acte);
        model.addAttribute("current_page",page);
        model.addAttribute("category",CategorieActe.values());

        return "edit_acte";
    }
}
