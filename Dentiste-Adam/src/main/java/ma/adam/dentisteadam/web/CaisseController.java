package ma.adam.dentisteadam.web;

import jakarta.validation.Valid;
import ma.adam.dentisteadam.entities.Caisse;
import ma.adam.dentisteadam.entities.Patient;
import ma.adam.dentisteadam.repositories.ICaisse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CaisseController {
    @Autowired
    ICaisse caisseRepository;
    @GetMapping("/caisse")
    private String caisse(Model model, @RequestParam(value = "page",defaultValue = "0") int page,
                          @RequestParam(value = "size",defaultValue = "5") int size)
    {
        var listCaisse=caisseRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("current_page",page);
        model.addAttribute("pages",new int[listCaisse.getTotalPages()]);
        model.addAttribute("page",page);
        model.addAttribute("listCaisse",listCaisse);

        return "caisse";
    }
    @GetMapping(path = "new_caisse")
    private String new_caisse(Model model, @RequestParam(value = "page",defaultValue = "0") int page,
                              @RequestParam(value = "size",defaultValue = "5") int size)
    {
        model.addAttribute("caisse",new Caisse());
        model.addAttribute("current_page",page);
        return "new_caisse";
    }
    @PostMapping(path = "save_caisse")
    private String save(@Valid Caisse c, BindingResult bindingResult, @RequestParam(value = "page" , defaultValue = "0") int page)
    {
        if(bindingResult.hasErrors())
            return "new_caisse";
        caisseRepository.save(c);
        return "redirect:/caisse?page="+page;
    }
    @GetMapping(path = "deleteCaisse")
    private String delete( @RequestParam(value = "page" , defaultValue = "0") int page,Long id)
    {
        caisseRepository.deleteById(id);
        return "redirect:/caisse?page="+page;

    }
    @GetMapping(path = "editCaisse")
    private String edit(@RequestParam(value = "page" , defaultValue = "0") int page,Long id,Model model)
    {
        model.addAttribute("caisse",caisseRepository.findById(id).orElse(null));
        model.addAttribute("current_page",page);
        return "edit_caisse";

    }
}
