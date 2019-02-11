package semav.licensingservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import semav.licensingservice.entity.License;
import semav.licensingservice.repository.LicenseRepository;


@RestController
@RequestMapping(value="/licenses", produces="application/json")
public class LicenseServiceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LicenseRepository licenseRepository;

    @GetMapping(produces="application/json")
    public Iterable<License> getLicenses() {
        log.info("getLicenses");
        return licenseRepository.findAll();
    }

    @GetMapping(value = "/{organisationId}", produces="application/json")
    public Iterable<License> getLicenses(@PathVariable int organisationId) {
        log.info("getLicenses({})", organisationId);
        return licenseRepository.findByOrganisationId(organisationId);
    }

    @PostMapping(consumes="application/json", produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public License postOrganisation(@RequestBody License license) {
        license.setId(null);
        return licenseRepository.save(license);
    }
}
