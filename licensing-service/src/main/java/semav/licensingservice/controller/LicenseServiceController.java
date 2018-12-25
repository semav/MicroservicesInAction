package semav.licensingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import semav.licensingservice.entity.License;
import semav.licensingservice.repository.LicenseRepository;
import java.util.List;


@RestController
@RequestMapping(value="/licenses", produces="application/json")
public class LicenseServiceController {

    @Autowired
    private LicenseRepository licenseRepository;

    @GetMapping()
    public Iterable<License> getLicenses() {
        return licenseRepository.findAll();
    }

    @GetMapping(value = "/{organisationId}")
    public Iterable<License> getLicenses(@PathVariable int organisationId) {
        return licenseRepository.
    }
}
