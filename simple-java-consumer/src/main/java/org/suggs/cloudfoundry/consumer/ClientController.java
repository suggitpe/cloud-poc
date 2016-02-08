package org.suggs.cloudfoundry.consumer;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    // rest template will resolve the location 'magically'
    private String greetingService = "simple-java-service";

    @RequestMapping("/rest")
    public Greeting foo() {
        return foo("world");
    }

    @RequestMapping("/rest/{personName}")
    public Greeting foo(@PathVariable String personName) {
        return restTemplate.getForObject(buildRestUrl(personName), Greeting.class);
    }

    private String buildRestUrl(String personName) {
        return "http://" + greetingService + "/?name=" + personName;
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Greeting {
    private long id;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "Value{id=" + id + ", content=" + content + "}";
    }
}

