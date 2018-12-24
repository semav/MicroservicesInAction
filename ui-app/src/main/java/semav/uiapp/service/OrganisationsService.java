package semav.uiapp.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import semav.uiapp.entity.License;
import semav.uiapp.entity.Organisation;

@Service
public class OrganisationsService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod="getNullOrganisations")
    public Organisation[] getOrganisations(){
        ResponseEntity<Organisation[]> responseEntity = restTemplate.getForEntity("http://ORGANISATIONS-SERVICE/licenses", Organisation[].class);
        return responseEntity.getBody();
    }

    public Organisation[] getNullOrganisations(){
        return null;
    }
}
