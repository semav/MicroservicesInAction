package semav.uiapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import semav.uiapp.entity.Organisation;
import semav.uiapp.service.OrganisationsService;

@Controller
public class OrganisationsController {
    @Autowired
    OrganisationsService getOrganisations;

    @GetMapping("/")
    public String main(Model model){
        Organisation[] organisations = getOrganisations.getOrganisations();
        model.addAttribute("organisations", organisations);
        return "main";
    }

    @GetMapping("/organisations/add")
    public String add(){
        return "add";
    }
}
