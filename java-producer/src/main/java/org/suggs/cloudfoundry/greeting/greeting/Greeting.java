package org.suggs.cloudfoundry.greeting.greeting;

public class Greeting {

    private final long id;
    private final String greeting;

    public Greeting(long id, String content) {
        this.id = id;
        this.greeting = content;
    }

    public long getId() {
        return id;
    }

    public String getGreeting() {
        return greeting;
    }
}
