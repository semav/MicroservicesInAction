package semav.licensingservice.messaging;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import semav.licensingservice.repository.LicenseRepository;

import javax.transaction.Transactional;
import java.io.IOException;

@Component
public class LicensingMessagingService {

    @Autowired
    LicenseRepository licenseRepository;

    @JmsListener(destination = "licensing")
    @Transactional
    public void receive(String message) {

        System.out.println(message);

        ObjectMapper mapper = new ObjectMapper();
        try {
            OrganisationMessage organisationMessage = mapper.readValue(message, OrganisationMessage.class);

            if (organisationMessage.getType() == OrganisationMessage.Type.Delete){
                licenseRepository.deleteByOrganisationId(organisationMessage.getId());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}