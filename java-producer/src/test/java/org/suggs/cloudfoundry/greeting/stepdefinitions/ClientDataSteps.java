package org.suggs.cloudfoundry.greeting.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import org.suggs.cloudfoundry.greeting.dsl.Retrieve;
import org.suggs.cloudfoundry.greeting.dsl.TheReply;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ClientDataSteps {

    public static final String PRODUCER_URL = "http://localhost:8901";
    private Actor actor;

    @Given("^the Client Service is running$")
    public void the_producer_is_running() {
        assertThat(RestAssured.get(PRODUCER_URL).statusCode(), is(200));
    }

    @When("^(.*) calls the client service for client ref (\\d+)$")
    public void john_calls_the_client_service_for_client_ref(String actorName, int clientRef) {
        actor = Actor.named(actorName);
        actor.can(CallAnApi.at(PRODUCER_URL));
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

