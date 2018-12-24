package semav.uiapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import semav.uiapp.entity.License;
import semav.uiapp.service.LicensingService;

@Controller
public class MainController {
    @Autowired
    LicensingService licensingService;
    @GetMapping()
    public String main(Model model){
        License[] licenses = licensingService.getLicenses();
        model.addAttribute("licenses", licenses);
        return "main";
    }
}
