package org.suggs.cloudpoc.producer.dsl;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetClientRef implements Interaction {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource("/clientData").with(requestSpecification ->
                requestSpecification.queryParam("id", "1")));
    }
}
