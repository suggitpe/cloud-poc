package org.suggs.cloudpoc.producer.pact;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider("client_provider")
public class ClientPactProducerTest extends PactTestRoot {

    private static final Logger LOG = LoggerFactory.getLogger(ClientPactProducerTest.class);

    @State("Client with ID:1 exists")
    public void createClientForID() {
        LOG.info("pretending to create a client with ID of 1 in the service");
    }
}
