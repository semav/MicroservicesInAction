package semav.licensingservice.repository;

import org.springframework.data.repository.CrudRepository;
import semav.licensingservice.entity.License;

import java.util.List;

public interface LicenseRepository extends CrudRepository<License, String> {
    List<License> findByOrganizationId(String organizationId);
    License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);

}

