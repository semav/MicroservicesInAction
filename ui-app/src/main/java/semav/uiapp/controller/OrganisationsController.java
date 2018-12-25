package semav.uiapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/organisation")
    public String organisation(@RequestParam("id") int id, Model model){
        Organisation organisation = organisationsService.getOrganisation(id);
        License[] licenses = licensingService.getLicenses(id);

        model.addAttribute("organisation", organisation);
        model.addAttribute("licenses", licenses);

        return "organisations/organisation";
    }

    @GetMapping("/organisations/add")
    public String add(){
        return "organisations/add";
    }
}
