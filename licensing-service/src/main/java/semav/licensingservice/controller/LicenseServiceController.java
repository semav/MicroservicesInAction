package semav.licensingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import semav.licensingservice.entity.License;
import semav.licensingservice.repository.LicenseRepository;

import java.util.UUID;

@RestController
@RequestMapping(value="/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    @Autowired
    private LicenseRepository licenseRepository;

    @GetMapping(value="/{licenseId}")
    public License getLicenses(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {

        License license = new License();

        license.setLicenseId(UUID.randomUUID().toString());
        license.setProductName("Teleco");
        license.setLicenseType("Seat");
        license.setOrganizationId(UUID.randomUUID().toString());

        licenseRepository.save(license);


        return license;
    }
}
