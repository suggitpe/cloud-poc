package org.suggs.cloudpoc.producer.pact;

import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRestPactRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@PactFolder("pacts")
public class PactTestRoot {

    @TestTarget
    public Target target;

    @LocalServerPort
    public void setLocalServerPort(int localPort) {
        target = new HttpTarget("http", "localhost", localPort, "/");
    }
}
