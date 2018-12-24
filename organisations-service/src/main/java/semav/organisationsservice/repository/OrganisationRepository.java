package semav.organisationsservice.repository;

import org.springframework.data.repository.CrudRepository;
import semav.organisationsservice.entity.Organisation;

public interface OrganisationRepository extends CrudRepository<Organisation, Integer> {

}

