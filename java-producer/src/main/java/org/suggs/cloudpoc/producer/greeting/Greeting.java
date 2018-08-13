package org.suggs.cloudpoc.producer.greeting;

public class Greeting {

    private final long id;
    private final String from;
    private final String greeting;

    public Greeting(long id, String from, String content) {
        this.id = id;
        this.from = from;
        this.greeting = content;
    }

    public long getId() {
        return id;
    }

    public String getFrom(){
        return from;
    }

    public String getGreeting() {
        return greeting;
    }
}
