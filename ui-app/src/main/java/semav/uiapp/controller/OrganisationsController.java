package semav.uiapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(this.getClass());

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
    public String processAdd(@ModelAttribute("organisation") Organisation organisation){
        Organisation newOrganisation = organisationsService.addOrganisation(organisation);
        return "redirect:/";
    }

    @GetMapping("/organisations/addLicense")
    public String addLicense(@RequestParam(value = "id") int organisationId, Model model){
        License license = new License();
        license.setOrganisationId(organisationId);
        model.addAttribute("license", license);

        return "organisations/addLicense";
    }

    @PostMapping("/organisations/addLicense")
    public String processLicense(@ModelAttribute("license") License license){
        License newLicense = licensingService.addLicense(license);
        return "redirect:/";
    }
}
