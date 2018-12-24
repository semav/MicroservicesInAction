package semav.uiapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import semav.uiapp.entity.License;

@Service
public class LicensingService {
    @Autowired
    RestTemplate restTemplate;

    public License[] getLicenses(){
        ResponseEntity<License[]> responseEntity = restTemplate.getForEntity("http://LICENSING-SERVICE/licenses", License[].class);
        return responseEntity.getBody();
    }
}
