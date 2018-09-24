package org.suggs.cloudpoc.producer.pact;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Provider("tradeevent_provider")
public class TradePactProducerTest extends PactTestRoot {

    private static final Logger LOG = LoggerFactory.getLogger(TradePactProducerTest.class);

    @State("Trade with ID:1 exists")
    public void toGetState() {
        LOG.info("pretending to create a trade with iID of 1 in the service");
    }
}
