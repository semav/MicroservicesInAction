package semav.organisationsservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import semav.organisationsservice.entity.Organisation;
import semav.organisationsservice.messaging.OrganisationMessage;
import semav.organisationsservice.messaging.LicensingMessagingService;
import semav.organisationsservice.repository.OrganisationRepository;

@Service
public class OrganisationsService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    LicensingMessagingService messagingService;

    public Iterable<Organisation> getOrganisations(){
        log.info("getOrganisations");
        return organisationRepository.findAll();
    }

    public void deleteOrganisation(int id) {
        try {
            organisationRepository.deleteById(id);

            OrganisationMessage message = new OrganisationMessage();
            message.setId(id);
            message.setType(OrganisationMessage.Type.Delete);
            messagingService.send(message);

        } catch (EmptyResultDataAccessException e) {}
    }
}
