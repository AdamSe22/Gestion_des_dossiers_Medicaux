package ma.adam.dentisteadam.web;

import jakarta.validation.Valid;
import ma.adam.dentisteadam.entities.*;
import ma.adam.dentisteadam.enums.GroupSanguin;
import ma.adam.dentisteadam.enums.Mutuelle;
import ma.adam.dentisteadam.repositories.IPersonne;
import org.springframework.ui.Model;
import ma.adam.dentisteadam.repositories.IPatient;
import ma.adam.dentisteadam.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;

@Controller
public class PatientController{
    @Autowired
    IPatient patientRepository;
    @Autowired
    IPersonne personne;
    @Autowired
    PatientService patientService;

    @GetMapping(path = "/patient")
    private String Patient(Model model, @RequestParam(value = "page",defaultValue = "0") int page,
                           @RequestParam(value = "size",defaultValue = "5") int size,
                           @RequestParam(value = "name",defaultValue = "")String name)
    {
        var listPatient=patientService.getPatient(name,page,size);
        model.addAttribute("current_page",page);
        model.addAttribute("pages",new int[listPatient.getTotalPages()]);
        model.addAttribute("page",page);
        model.addAttribute("listPatient",listPatient);
        model.addAttribute("name",name);
        return "patient";
    }
    @GetMapping(path = "/patientDelete")
    private String supprimer(Long id,String name,int page)
    {
        System.out.println("********************************************************************");
        Personne p =personne.findById(id).orElse(null);
        System.out.println(p.getId()+"---------------->"+p.getNom()+"_"+p.getPrenom());
        personne.deleteById(id);
        return "redirect:/patient?name="+name+"&page="+page;
    }

    @GetMapping(path = "/newPatient")
    public String newPatient(Model model)
    {

        Patient p=new Patient();
        model.addAttribute("patient_new",p);
        model.addAttribute("groupSanguin",GroupSanguin.values());
        model.addAttribute("mutuelle",Mutuelle.values());
        return "new_patient";
    }
    @PostMapping(path = "/save")
    public String save(@Valid Patient p, BindingResult bindingResult,@RequestParam(value = "page" , defaultValue = "0") int page,String name)
    {
        if(bindingResult.hasErrors())
            return "newPatient";
        patientRepository.save(p);
        return "redirect:/patient?name="+name+"&page="+page;

    }
    @GetMapping(path = "/editPatient")
    private String edit_patient(@RequestParam(value = "page",defaultValue = "0")int page,@RequestParam
            (value = "name",defaultValue ="")String name,Model model,Long id)
    {
        Patient patient=patientRepository.findById(id).orElse(null);
        model.addAttribute("patient_new",patient);
        model.addAttribute("page",page);
        model.addAttribute("name",name);
        model.addAttribute("groupSanguin",GroupSanguin.values());
        model.addAttribute("mutuelle",Mutuelle.values());
        return "edit_patient";
    }
}
