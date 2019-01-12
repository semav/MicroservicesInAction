package semav.organisationsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import semav.organisationsservice.entity.Organisation;
import semav.organisationsservice.messaging.OrganisationMessage;
import semav.organisationsservice.messaging.LicensingMessagingService;
import semav.organisationsservice.repository.OrganisationRepository;

@Service
public class OrganisationsService {
    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    LicensingMessagingService messagingService;

    public Iterable<Organisation> getOrganisations(){
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
