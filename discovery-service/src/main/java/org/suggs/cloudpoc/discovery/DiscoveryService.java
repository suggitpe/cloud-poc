package org.suggs.cloudpoc.discovery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import static org.springframework.boot.WebApplicationType.SERVLET;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryService {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DiscoveryService.class)
                .web(SERVLET)
                .run(args);
    }

}

