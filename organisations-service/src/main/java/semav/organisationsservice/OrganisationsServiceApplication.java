package semav.organisationsservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.messaging.MessageHandler;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.sql.DataSource;

@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
@EnableHystrix
@EnableOAuth2Client
public class OrganisationsServiceApplication {
    @Bean
    @LoadBalanced
    public OAuth2RestTemplate oauth2RestTemplate(@Qualifier("oauth2ClientContext") OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }

    @Bean
    public MessageHandler eventsHandler() {
        return new EventsHandler();
    }


    @Bean
    public MessageSource<?> eventsInboundAdapter(DataSource dataSource) {
        JdbcPollingChannelAdapter adapter = new JdbcPollingChannelAdapter(dataSource, "SELECT * FROM events WHERE status = 0;");
        adapter.setUpdateSql("UPDATE events SET status = 1");
        adapter.setUpdatePerRow(true);
        return adapter;
    }

    @Bean
    public IntegrationFlow pollingFlow(DataSource dataSource) {
        return IntegrationFlows.from(eventsInboundAdapter(dataSource),
                c -> c.poller(Pollers.fixedRate(1000)
                        .transactional()))
                .channel("eventsChannel")
                .handle(eventsHandler())
                .get();
    }


    public static void main(String[] args) {
        SpringApplication.run(OrganisationsServiceApplication.class, args);
    }

}

