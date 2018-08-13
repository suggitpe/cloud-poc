package org.suggs.cloudpoc.consumer.pact;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.suggs.cloudpoc.consumer.greeting.Greeting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.io.Resources.getResource;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class GreetingPactTest {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingPactTest.class);

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("greeting_provider", this);

    @Pact(consumer = "greeting_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("test GET").uponReceiving("Request for a greeting").path("/greeting").method("GET")
                .willRespondWith().status(200).headers(headers).body(readContentsOf("helloWorld.json"))
                .toPact();
    }

    @Test
    @PactVerification()
    public void checkWeCanProcessTheGreetingPact() throws IOException {
        ResponseEntity<String> response = new RestTemplate().getForEntity(mockProvider.getUrl() + "/greeting", String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();

        Greeting greeting = new ObjectMapper().readValue(response.getBody(), Greeting.class);
        assertThat(greeting.getFrom()).isEqualTo("producer");
        assertThat(greeting.getGreeting()).isEqualTo("Hello, World");
    }

    @Test
    public void readsFromTestResources() throws IOException {
        LOG.debug(readContentsOf("logback-test.xml"));
    }

    private String readContentsOf(String aFileName) throws IOException {
        return Resources.toString(getResource(aFileName), UTF_8);
    }

}
