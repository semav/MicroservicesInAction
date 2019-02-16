package semav.organisationsservice.messaging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import semav.organisationsservice.entity.Event;

public class EventsHandler{
    @Autowired
    LicensingMessagingService licensingMessagingService;
    public void handleMessage(List<Event> events) throws MessagingException {

        for (Event event : events) {
            licensingMessagingService.send(event.getData());    
        }        
    }    
}
