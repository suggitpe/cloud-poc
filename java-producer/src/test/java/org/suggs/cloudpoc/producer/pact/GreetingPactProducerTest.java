package org.suggs.cloudpoc.producer.pact;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider("greeting_provider")
public class GreetingPactProducerTest extends PactTestRoot {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingPactProducerTest.class);

    @State("test GET")
    public void toGetState() {
        LOG.info("We do not need to do anything to get to this state");
    }

}
