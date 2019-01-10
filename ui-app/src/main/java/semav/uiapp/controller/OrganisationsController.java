package semav.uiapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import semav.uiapp.entity.License;
import semav.uiapp.entity.Organisation;
import semav.uiapp.service.LicensingService;
import semav.uiapp.service.OrganisationsService;

@Controller
public class OrganisationsController {
    @Autowired
    OrganisationsService organisationsService;

    @Autowired
    LicensingService licensingService;

    @GetMapping("/")
    public String main(Model model){
        Organisation[] organisations = organisationsService.getOrganisations();
        model.addAttribute("organisations", organisations);
        return "organisations/main";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        organisationsService.deleteOrganisation(id);
        return "redirect:/";
    }

    @GetMapping("/organisation")
    public String organisation(@RequestParam("id") int id, Model model){
        Organisation organisation = organisationsService.getOrganisation(id);
        License[] licenses = licensingService.getLicenses(id);

        model.addAttribute("organisation", organisation);
        model.addAttribute("licenses", licenses);

        return "organisations/organisation";
    }

    @GetMapping("/organisations/add")
    public String add(Model model){
        Organisation organisation = new Organisation();
        model.addAttribute("organisation", organisation);

        return "organisations/add";
    }

    @PostMapping("/organisations/add")
    public String processAdd(@ModelAttribute("customer") Organisation organisation){
        Organisation newOrganisation = organisationsService.add(organisation);
        return "redirect:/";
    }
}
