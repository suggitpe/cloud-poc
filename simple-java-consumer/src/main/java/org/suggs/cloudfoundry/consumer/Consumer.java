package org.suggs.cloudfoundry.consumer;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import static org.springframework.boot.WebApplicationType.SERVLET;

@SpringBootApplication
@EnableDiscoveryClient
public class Consumer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Consumer.class)
                .web(SERVLET)
                .run(args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder aBuilder){
        return aBuilder.build();
    }
}
