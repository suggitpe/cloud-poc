package org.suggs.cloudpoc.consumer.pact;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;
import org.suggs.cloudpoc.consumer.trade.domain.TradeEvent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TradeEventPactConsumerTest {

    private static final Logger LOG = LoggerFactory.getLogger(TradeEventPactConsumerTest.class);
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS";

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("tradeevent_provider", this);

    @Pact(consumer = "tradeevent_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws IOException {
        LOG.info("Creating mock response for the test using well known trade event data");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("Trade with ID:1 exists").uponReceiving("Request for a client with an ID of 1")
                .path("/tradeEvent").method("GET").query("id=1&domain=testDomain&version=1")
                .willRespondWith().status(200).headers(headers).body(createTradeBody())
                .toPact();
    }

    private PactDslJsonBody createTradeBody() {
        return new PactDslJsonBody()
                .object("tradeIdentifier", createTradeIdentifier())
                .stringValue("eventType", "New")
                .timestamp("executionTimestamp", DATE_TIME_FORMAT)
                .minArrayLike("legs", 2)
                .stringType("legRole")
                .stringType("currency")
                .numberType("amount")
                .closeArray()
                .asBody();
    }

    private DslPart createTradeIdentifier() {
        return new PactDslJsonBody()
                .id("id", 1L)
                .stringValue("domain", "testDomain")
                .numberValue("version", 1);
    }

    @Test
    @PactVerification()
    public void checkWeCanProcessTheTradeEventPact() throws IOException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", 1);
        params.put("domain", "testDomain");
        params.put("version", 1);

        LOG.info("Calling the mock webservice");
        ResponseEntity<String> response = new RestTemplate().getForEntity(mockProvider.getUrl() + "/tradeEvent?id={id}&domain={domain}&version={version}", String.class, params);

        LOG.info("Checking the response from the webservice call");
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type")).contains("application/json");
        assertThat(response.getBody()).as("Response body is expected to be populated").isNotEmpty();

        LOG.info("Building the trade event domain objects from the response");
        TradeEvent tradeEvent = createTradeEventFromJson(response.getBody());
        assertThat(tradeEvent.getEventType()).isEqualTo("New");
    }

    private TradeEvent createTradeEventFromJson(String json) throws IOException {
        ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();
        return mapper.readValue(json, TradeEvent.class);
    }
}
