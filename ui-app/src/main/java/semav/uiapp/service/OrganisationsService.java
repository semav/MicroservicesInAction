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

    @HystrixCommand(fallbackMethod="getNullOrganisation")
    public Organisation getOrganisation(int id){
        ResponseEntity<Organisation> responseEntity = restTemplate.getForEntity("http://ORGANISATIONS-SERVICE/organisations/{id}", Organisation.class, id);
        return responseEntity.getBody();
    }

    @HystrixCommand(fallbackMethod="getNullOrganisations")
    public Organisation[] getOrganisations(){
        ResponseEntity<Organisation[]> responseEntity = restTemplate.getForEntity("http://ORGANISATIONS-SERVICE/organisations", Organisation[].class);
        return responseEntity.getBody();
    }

    public Organisation[] getNullOrganisations(){
        return null;
    }

    public Organisation getNullOrganisation(int id){
        return null;
    }
}
