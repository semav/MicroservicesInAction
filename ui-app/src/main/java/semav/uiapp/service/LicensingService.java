package semav.uiapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import semav.uiapp.entity.License;

@Service
public class LicensingService {
    @Autowired
    RestTemplate restTemplate;

    public License[] getLicenses(int organisationId){
        ResponseEntity<License[]> responseEntity = restTemplate.getForEntity("http://LICENSING-SERVICE/licenses/{organisationId}", License[].class, organisationId);
        return responseEntity.getBody();
    }
}
