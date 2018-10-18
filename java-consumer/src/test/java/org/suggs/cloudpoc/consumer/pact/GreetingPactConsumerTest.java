package org.suggs.cloudpoc.consumer.pact;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.suggs.cloudpoc.consumer.greeting.domain.Greeting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingPactConsumerTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("greeting_provider", this);

    @Pact(consumer = "greeting_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws IOException {
        return builder
                .given("test GET").uponReceiving("Request for a greeting").path("/greeting").method("GET")
                .willRespondWith().status(200).headers(createHeaders()).body(createExpectedGreetingBody())
                .toPact();
    }

    @NotNull
    private Map<String, String> createHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    private PactDslJsonBody createExpectedGreetingBody() {
        return new PactDslJsonBody()
                .id()
                .stringValue("from", "producer")
                .stringValue("greeting", "Hello, World")
                .asBody();
    }

    @Test
    @PactVerification()
    public void checkWeCanProcessTheGreetingPact() throws IOException {
        ResponseEntity<String> response = retrieveGreetingData();

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();

        Greeting greeting = createGretingFromJson(response.getBody());
        assertThat(greeting.getFrom()).isEqualTo("producer");
        assertThat(greeting.getGreeting()).isEqualTo("Hello, World");
    }

    private ResponseEntity<String> retrieveGreetingData() {
        return new RestTemplate().getForEntity(mockProvider.getUrl() + "/greeting", String.class);
    }

    private Greeting createGretingFromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, Greeting.class);
    }

}
