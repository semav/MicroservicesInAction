package semav.organisationsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import semav.organisationsservice.entity.Organisation;
import semav.organisationsservice.repository.OrganisationRepository;

@RestController(value="/organisations")
public class OrganisationsController {
    @Autowired
    OrganisationRepository organisationRepository;

    @GetMapping
    public Iterable<Organisation> getOrganisations(){
        return organisationRepository.findAll();
    }
}
