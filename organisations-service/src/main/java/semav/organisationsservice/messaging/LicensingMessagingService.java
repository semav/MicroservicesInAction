package semav.organisationsservice.messaging;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LicensingMessagingService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(OrganisationMessage organisationMessage) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String message = mapper.writeValueAsString(organisationMessage);
            jmsTemplate.convertAndSend("licensing", message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}