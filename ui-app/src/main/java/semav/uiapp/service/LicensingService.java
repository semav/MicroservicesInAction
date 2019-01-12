package semav.uiapp.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import semav.uiapp.entity.License;
import semav.uiapp.entity.Organisation;

@Service
public class LicensingService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod="getLicensesFallback")
    public License[] getLicenses(int organisationId){
        ResponseEntity<License[]> responseEntity = restTemplate.getForEntity("http://LICENSING-SERVICE/licenses/{organisationId}", License[].class, organisationId);
        return responseEntity.getBody();
    }

    @HystrixCommand(fallbackMethod="addLicenseFallback")
    public License addLicense(License license) {
        return restTemplate.postForObject("http://LICENSING-SERVICE/licenses", license, License.class);
    }

    public License[] getLicensesFallback(int organisationId){
        return null;
    }

    @HystrixCommand(fallbackMethod="addLicenseFallback")
    public License addLicenseFallback(License license) {
        return null;
    }
}
