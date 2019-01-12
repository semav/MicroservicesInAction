package semav.licensingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import semav.licensingservice.entity.License;
import semav.licensingservice.repository.LicenseRepository;
import java.util.List;


@RestController
@RequestMapping(value="/licenses", produces="application/json")
public class LicenseServiceController {

    @Autowired
    private LicenseRepository licenseRepository;

    @GetMapping(produces="application/json")
    public Iterable<License> getLicenses() {
        return licenseRepository.findAll();
    }

    @GetMapping(value = "/{organisationId}", produces="application/json")
    public Iterable<License> getLicenses(@PathVariable int organisationId) {
        return licenseRepository.findByOrganisationId(organisationId);
    }

    @PostMapping(consumes="application/json", produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public License postOrganisation(@RequestBody License license) {
        license.setId(null);
        return licenseRepository.save(license);
    }
}
