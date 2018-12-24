package semav.licensingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import semav.licensingservice.entity.License;
import semav.licensingservice.service.LicenseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="/licenses")
public class LicenseServiceController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping()
    public List<License> getLicenses() {
        return licenseService.getLicenses();
    }
}
