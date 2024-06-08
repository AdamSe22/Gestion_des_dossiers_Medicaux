package ma.adam.dentisteadam.web;

import jakarta.validation.Valid;
import ma.adam.dentisteadam.entities.Consultation;
import ma.adam.dentisteadam.entities.Patient;
import ma.adam.dentisteadam.enums.TypeConsultation;
import ma.adam.dentisteadam.repositories.IConsultation;
import ma.adam.dentisteadam.repositories.IDossierMedical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;

@Controller
public class ConsultationController {
    @Autowired
    IConsultation consultationRepository;
    @Autowired
    IDossierMedical dossierMedicalRepository;

    @GetMapping(path = "/consultation")
    private String consultations(Model model, @RequestParam(value = "page",defaultValue = "0") int page,
                                 @RequestParam(value = "size",defaultValue = "5") int size,
                                 @RequestParam(value = "name",defaultValue = "")String type)
    {
        var listConsultation=consultationRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("current_page",page);
        model.addAttribute("pages",new int[listConsultation.getTotalPages()]);
        model.addAttribute("page",page);
        model.addAttribute("listConsultation",listConsultation);
        model.addAttribute("type",type);
        return "consultation";
    }
    @GetMapping(path = "deleteConsultation")
    private String delete(Long id,@RequestParam(value = "page",defaultValue = "0") int page,
                          @RequestParam(value = "size",defaultValue = "5") int size)
    {
        consultationRepository.deleteById(id);
        return "redirect:/consultation?page="+page;

    }
    @GetMapping(path = "new_consultation")
    private String add(Model model)
    {
        model.addAttribute("consultation",new Consultation());
        model.addAttribute("type_consultation", TypeConsultation.values());
        model.addAttribute("dossier_medical",dossierMedicalRepository.findAll());
        return "new_consultation";

    }
    @PostMapping(path = "enrg")
    private String enrg(@Valid Consultation c, BindingResult bindingResult, @RequestParam(value = "page" , defaultValue = "0") int page)
    {
        if(bindingResult.hasErrors())
            return "new_consultation";
        consultationRepository.save(c);
        return "redirect:/consultation?page"+page;

    }
    @GetMapping(path = "edit_consultation")
    private String edit(Model model,Long id,@RequestParam(value = "page",defaultValue = "0")int page)
    {
        model.addAttribute("consultation",consultationRepository.findById(id).orElse(null));
        model.addAttribute("type_consultation", TypeConsultation.values());
        model.addAttribute("page", page);
        model.addAttribute("dossier_medical",dossierMedicalRepository.findAll());
        return "edit_consultation";

    }
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PostMapping(path = "eddit")
    private String eddit(@Valid Consultation c, BindingResult bindingResult, @RequestParam(value = "page" , defaultValue = "0") int page)
    {
        if(bindingResult.hasErrors())
            return "edit_consultation";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(c.getDateConsultation());
        jdbcTemplate.execute("UPDATE consultation SET date_consultation ='"
                + formattedDate
                + "', type_consultation = '"
                + c.getTypeConsultation()
                + "', dossier_medical_id = "
                + c.getDossierMedical().getId()
                + " WHERE id = " + c.getId());
        consultationRepository.save(c);

        return "redirect:/consultation?page"+page;
    }


}
