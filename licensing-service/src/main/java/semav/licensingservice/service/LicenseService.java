package semav.licensingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semav.licensingservice.entity.License;
import semav.licensingservice.repository.LicenseRepository;

import java.util.*;

@Service
public class LicenseService {
    @Autowired
    private LicenseRepository licenseRepository;

    public License getLicense(String organizationId, String licenseId) {
        return licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
    }

    public List<License> getLicensesByOrg(String organizationId){
        return licenseRepository.findByOrganizationId( organizationId );
    }

    public void saveLicense(License license){
        license.setLicenseId(UUID.randomUUID().toString());
        license.setOrganizationId(UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    public List<License> getLicenses() {
        List<License> result = new ArrayList<>();
        licenseRepository.findAll().forEach(result::add);
        return result;
    }
}
