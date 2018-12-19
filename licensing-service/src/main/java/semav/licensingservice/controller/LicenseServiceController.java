package semav.licensingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import semav.licensingservice.entity.License;

@RestController
@RequestMapping(value="/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
    @GetMapping(value="/{licenseId}")
    public License getLicenses(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {

        License license = new License();

        license.setLicenseId(licenseId);
        license.setProductName("Teleco");
        license.setLicenseType("Seat");
        license.setOrganizationId(organizationId);

        return license;
    }
}
