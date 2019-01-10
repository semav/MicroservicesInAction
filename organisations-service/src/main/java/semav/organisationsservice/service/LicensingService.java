package semav.organisationsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import semav.organisationsservice.entity.License;

@Service
public class LicensingService {
    @Autowired
    OAuth2RestTemplate oAuth2RestTemplate;

    public License[] getLicenses(int organisationId){
        ResponseEntity<License[]> responseEntity = oAuth2RestTemplate.getForEntity("http://licensing-service/licenses/{organisationId}", License[].class, organisationId);
        return responseEntity.getBody();
    }
}
