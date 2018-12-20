package semav.licensingservice.repository;

import org.springframework.data.repository.CrudRepository;
import semav.licensingservice.entity.License;

import java.util.List;

public interface LicenseRepository extends CrudRepository<License, String> {
    /*
    public List<License> findByOrganizationId(String organizationId);
    public License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);
    */
}

