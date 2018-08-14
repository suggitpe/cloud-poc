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
import org.suggs.cloudpoc.consumer.client.Client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.io.Resources.getResource;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientPactConsumerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ClientPactConsumerTest.class);

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("client_provider", this);

    @Pact(consumer = "client_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws IOException {
        LOG.info("Creating mock response for Pact");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("Client with ID:1 exists").uponReceiving("Request for a client with an ID of 1")
                .path("/clientData").method("GET").query("id=1")
                .willRespondWith().status(200).headers(headers).body(readContentsOf("client-1.json"))
                .toPact();
    }

    @Test
    @PactVerification()
    public void checkWeCanProcessTheClientPact() throws IOException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", 1);
        ResponseEntity<String> response = new RestTemplate().getForEntity(mockProvider.getUrl() + "/clientData?id={id}", String.class, params);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();

        LOG.info("Calling mock webservice to verify the Pact works for us");
        Client client = new ObjectMapper().readValue(response.getBody(), Client.class);
        assertThat(client.getName()).isEqualTo("MyName");
        assertThat(client.getLocation()).isEqualTo("London");
        assertThat(client.getSecretCode()).isEqualTo("MySecretCode");
    }

    private String readContentsOf(String aFileName) throws IOException {
        return Resources.toString(getResource(aFileName), UTF_8);
    }
}
