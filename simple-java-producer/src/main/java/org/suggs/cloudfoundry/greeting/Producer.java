package org.suggs.cloudfoundry.greeting;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Producer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Producer.class)
                .web(true)
                .run(args);
    }
}
