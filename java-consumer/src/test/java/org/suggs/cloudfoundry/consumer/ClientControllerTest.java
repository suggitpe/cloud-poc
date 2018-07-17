package org.suggs.cloudfoundry.consumer;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.google.common.io.Resources;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.io.Resources.getResource;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientControllerTest {
    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("test_provider", this);

    @Pact(consumer = "client_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("Client with ID:1 exists").uponReceiving("Request for a client with an ID of 1").path("/client/1").method("GET")
                .willRespondWith().status(200).headers(headers).body(readContentsOf("client-1.json"))
                .toPact();
    }

    @Test
    @PactVerification()
    public void run() {
        ResponseEntity<String> response = new RestTemplate().getForEntity(mockProvider.getUrl() + "/client/1", String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();
        assertThat(response.getBody()).contains("name", "Foo");
        assertThat(response.getBody()).contains("secretCode", "shhhh");
        assertThat(response.getBody()).contains("location", "London");
    }

    private String readContentsOf(String aFileName) throws IOException {
        return Resources.toString(getResource(aFileName), UTF_8);
    }
}
