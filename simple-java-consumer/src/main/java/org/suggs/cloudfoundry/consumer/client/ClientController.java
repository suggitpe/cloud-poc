package org.suggs.cloudfoundry.consumer.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    private String greetingService = "simple-java-producer";

    @RequestMapping(value = "/client/{clientId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Client getGreetingDataAsJson(@PathVariable Long clientId) {
        return executeClientRestCallFor(clientId);
    }

    private Client executeClientRestCallFor(Long clientId) {
        return restTemplate.getForObject(buildRestUrl(clientId), Client.class);
    }

    private String buildRestUrl(Long clientId) {
        return "http://" + greetingService + "/clientData/?id=" + clientId;
    }


}
