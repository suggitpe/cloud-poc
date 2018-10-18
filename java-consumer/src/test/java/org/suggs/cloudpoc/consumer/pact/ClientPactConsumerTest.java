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
import org.suggs.cloudpoc.consumer.client.domain.Client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientPactConsumerTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("client_provider", this);

    @Pact(consumer = "client_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws IOException {
        return builder
                .given("Client with ID:1 exists").uponReceiving("Request for a client with an ID of 1")
                .path("/clientData").method("GET").query("id=1")
                .willRespondWith().status(200).headers(createHeaders()).body(createExpectedClientBody())
                .toPact();
    }

    @NotNull
    private Map<String, String> createHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    private PactDslJsonBody createExpectedClientBody() {
        return new PactDslJsonBody()
                .id("id", 1L)
                .stringValue("name", "MyName")
                .stringValue("location", "London")
                .asBody();
    }

    @Test
    @PactVerification()
    public void checkWeCanProcessTheClientPact() throws IOException {
        ResponseEntity<String> response = retrieveClientData();

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type")).contains("application/json");

        Client client = createClientFromJson(response.getBody());
        assertThat(client.getName()).isEqualTo("MyName");
        assertThat(client.getLocation()).isEqualTo("London");
    }

    private ResponseEntity<String> retrieveClientData() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", 1);

        return new RestTemplate().getForEntity(mockProvider.getUrl() + "/clientData?id={id}", String.class, params);
    }

    private Client createClientFromJson(String json) throws IOException {
        return new ObjectMapper().readValue(json, Client.class);
    }
}
