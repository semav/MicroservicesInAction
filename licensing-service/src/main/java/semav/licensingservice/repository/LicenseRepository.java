package semav.licensingservice.repository;

import org.springframework.data.repository.CrudRepository;
import semav.licensingservice.entity.License;

import java.util.List;

public interface LicenseRepository extends CrudRepository<License, Integer> {
    List<License> findByOrganisationId(Integer organisationId);
    void deleteByOrganisationId(int organisationId);
}

