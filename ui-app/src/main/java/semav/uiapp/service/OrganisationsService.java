package semav.uiapp.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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

    @HystrixCommand(fallbackMethod="getOrganisationsFallback")
    public Organisation[] getOrganisations(){
        ResponseEntity<Organisation[]> responseEntity = restTemplate.getForEntity("http://ORGANISATIONS-SERVICE/organisations", Organisation[].class);
        return responseEntity.getBody();
    }

    @HystrixCommand(fallbackMethod="addNullOrganisations")
    public Organisation addOrganisation(Organisation organisation) {
        return restTemplate.postForObject("http://ORGANISATIONS-SERVICE/organisations", organisation, Organisation.class);
    }

    @HystrixCommand(fallbackMethod="deleteFallback")
    public void deleteOrganisation(int id) {
        restTemplate.delete("http://ORGANISATIONS-SERVICE/organisations/{id}", id);
    }

    public void deleteFallback(int id){}

    public Organisation getNullOrganisation(int id){
        return null;
    }

    public Organisation addNullOrganisations(Organisation organisation) {
        return null;
    }

    public Organisation[] getOrganisationsFallback(){
        return null;
    }

}
