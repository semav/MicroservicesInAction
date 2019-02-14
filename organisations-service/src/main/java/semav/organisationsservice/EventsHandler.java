package semav.organisationsservice;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;


public class EventsHandler implements MessageHandler {
    static int i = 0;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        /*
        if (i++ % 2 == 0){
            System.out.println(message.getPayload());
        }
        else {
            throw new MessagingException("test exception");
        }*/
        /*asd*/

        System.out.println(message.getPayload());
    }
}
