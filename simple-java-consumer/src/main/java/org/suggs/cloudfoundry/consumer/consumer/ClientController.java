package org.suggs.cloudfoundry.consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ClientController {

    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private RestTemplate restTemplate;

    // rest template will resolve the location 'magically'
    private String greetingService = "simple-java-producer";

    @RequestMapping(value = "/greeting/{personName}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Greeting getGreetingDataAsJson(@PathVariable String personName) {
        return executeRestCallFor(personName);
    }

    @RequestMapping(value = "/greeting/{personName}", method = RequestMethod.GET)
    public String getGreetingDataAsWebPage(@PathVariable String personName, Model model) {
        model.addAttribute("data", executeRestCallFor(personName));
        model.addAttribute("id", personName);
        return "dataView";
    }

    private Greeting executeRestCallFor(String personName) {
        return restTemplate.getForObject(buildRestUrl(personName), Greeting.class);
    }

    private String buildRestUrl(String personName) {
        return "http://" + greetingService + "/producer/?name=" + personName;
    }

}

