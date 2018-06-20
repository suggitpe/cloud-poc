package org.suggs.cloudfoundry.consumer;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("test_provider", this);

    @Pact(consumer = "test_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("test GET").uponReceiving("GET REQUEST").path("/greeting").method("GET")
                .willRespondWith().status(200).headers(headers).body("{\"id\":1, \"content\":\"Hello, World\"}")
                .toPact();
    }

    @Test
    @PactVerification()
    public void run() {
        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("content", "Hello, World");
        ResponseEntity<String> response = new RestTemplate().getForEntity(mockProvider.getUrl() + "/greeting", String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();
        assertThat(response.getBody()).contains("content", "Hello, World");
    }

}
