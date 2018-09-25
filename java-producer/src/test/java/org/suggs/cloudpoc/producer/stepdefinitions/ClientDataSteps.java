package org.suggs.cloudpoc.producer.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.suggs.cloudpoc.producer.dsl.Retrieve;
import org.suggs.cloudpoc.producer.dsl.TheReply;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ClientDataSteps {

    public static final String PRODUCER_URL = "http://localhost:";
    private Actor actor;

    @LocalServerPort
    //private int localPort = -1;
    private int localPort = 8901;

    @Given("^the Client Service is running$")
    public void the_producer_is_running() {
        assertThat(localPort, is(not(-1)));
        assertThat(RestAssured.get(PRODUCER_URL + localPort).statusCode(), is(200));
    }

    @When("^(.*) calls the client service for client ref (\\d+)$")
    public void john_calls_the_client_service_for_client_ref(String actorName, int clientRef) {
        actor = Actor.named(actorName);
        actor.can(CallAnApi.at(PRODUCER_URL + localPort));
        actor.attemptsTo(Retrieve.client(clientRef));
    }

    @Then("^it tells John that the client is called (.*)$")
    public void it_tells_John_that_the_client_is_called(String name) {
        actor.should(seeThat(TheReply.name(), is(equalTo(name))));
    }

    @Then("^it tells John that the location is (.*)$")
    public void it_tells_John_that_the_location_is(String location) {
        actor.should(seeThat(TheReply.location(), is(equalTo(location))));
    }

}

