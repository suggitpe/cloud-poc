package org.suggs.cloudfoundry.discovery;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class DiscoveryService {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DiscoveryService.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}

