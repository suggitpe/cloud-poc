package org.suggs.cloudfoundry.greeting.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import org.suggs.cloudfoundry.greeting.dsl.Say;
import org.suggs.cloudfoundry.greeting.dsl.TheReply;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GreetingSteps {

    public static final String PRODUCER_URL = "http://localhost:8901";
    private Actor actor;

    @Given("^the Producer is running$")
    public void the_producer_is_running() {
    }

    @When("^(.*) calls the Producer$")
    public void calls_the_producer(String actorName) {
        actor = Actor.named(actorName);
        actor.can(CallAnApi.at(PRODUCER_URL));
        actor.attemptsTo(Say.hello());
    }

    @When("^someone called the Producer anonymously$")
    public void someone_called_the_Producer_anonymously() {
        actor = Actor.named("Anonymous");
        actor.can(CallAnApi.at(PRODUCER_URL));
        actor.attemptsTo(Say.helloAnonymously());
    }

    @Then("^it says \"(.*)\"$")
    public void it_says(String response) {
        actor.should(seeThat(TheReply.content(), is(equalTo(response))));
    }

}
