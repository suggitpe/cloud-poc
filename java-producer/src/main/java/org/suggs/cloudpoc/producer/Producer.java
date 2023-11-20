package org.suggs.cloudpoc.producer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static org.springframework.boot.WebApplicationType.SERVLET;

@EnableDiscoveryClient
@SpringBootApplication
public class Producer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Producer.class)
                .web(SERVLET)
                .run(args);
    }

}
