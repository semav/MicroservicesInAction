package semav.organisationsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semav.organisationsservice.entity.License;
import semav.organisationsservice.entity.Organisation;
import semav.organisationsservice.repository.OrganisationRepository;
import semav.organisationsservice.service.LicensingService;
import semav.organisationsservice.service.OrganisationsService;

import java.util.Optional;

@RestController
@RequestMapping(value="/organisations", produces="application/json")
public class OrganisationsController {
    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    LicensingService licensingService;

    @Autowired
    OrganisationsService organisationsService;

    @GetMapping
    public Iterable<Organisation> getOrganisations(){
        return organisationsService.getOrganisations();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Organisation> getOrganisation(@PathVariable int id){
        Optional<Organisation> organisation = organisationRepository.findById(id);

        if (organisation.isPresent()) {

            License[] l = licensingService.getLicenses(id);
            Organisation o = organisation.get();
            o.setLicenses(l);

            return new ResponseEntity<>(o, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Organisation postOrganisation(@RequestBody Organisation organisation) {
        organisation.setId(null);
        return organisationRepository.save(organisation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteOrganisation(@PathVariable("id") int id) {
        organisationsService.deleteOrganisation(id);
    }
}
