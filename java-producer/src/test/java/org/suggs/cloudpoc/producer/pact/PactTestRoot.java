package org.suggs.cloudpoc.producer.pact;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.suggs.cloudpoc.producer.Producer;

@RunWith(PactRunner.class)
@PactFolder("pacts")
public class PactTestRoot {

    @TestTarget
    public final Target target = new HttpTarget("http", "localhost", 8901, "/");

    public static ConfigurableApplicationContext application;

    @BeforeClass
    public static void startApplication() {
        application = SpringApplication.run(Producer.class);
    }

    @AfterClass
    public static void stopApplication(){
        application.stop();
    }
}
