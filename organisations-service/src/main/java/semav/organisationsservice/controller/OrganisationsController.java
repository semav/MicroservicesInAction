package semav.organisationsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import semav.organisationsservice.entity.Organisation;
import semav.organisationsservice.repository.OrganisationRepository;

import java.util.Optional;

@RestController
@RequestMapping(value="/organisations", produces="application/json")
public class OrganisationsController {
    @Autowired
    OrganisationRepository organisationRepository;

    @GetMapping
    public Iterable<Organisation> getOrganisations(){
        return organisationRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Organisation> getOrganisation(@PathVariable int id){
        Optional<Organisation> organisation = organisationRepository.findById(id);
        if (organisation.isPresent()) {
            return new ResponseEntity<>(organisation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
