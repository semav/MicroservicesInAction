package semav.organisationsservice.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import semav.organisationsservice.entity.Event;
import semav.organisationsservice.entity.Organisation;
import semav.organisationsservice.messaging.OrganisationMessage;
import semav.organisationsservice.messaging.LicensingMessagingService;
import semav.organisationsservice.repository.EventRepository;
import semav.organisationsservice.repository.OrganisationRepository;
import java.io.IOException;
import javax.transaction.Transactional;

@Service
public class OrganisationsService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    LicensingMessagingService messagingService;

    public Iterable<Organisation> getOrganisations() {
        log.info("getOrganisations");
        return organisationRepository.findAll();
    }

    @Transactional
    public void deleteOrganisation(int id) {
        try {
            OrganisationMessage message = new OrganisationMessage();
            message.setId(id);
            message.setType(OrganisationMessage.Type.Delete);

            ObjectMapper mapper = new ObjectMapper();
            
            Event event = new Event();
            event.setStatus(0);
            event.setData(mapper.writeValueAsString(message));

            organisationRepository.deleteById(id);
            eventRepository.save(event);

        } catch (EmptyResultDataAccessException e) {}
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
