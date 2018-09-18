package org.suggs.cloudpoc.consumer.greeting.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Greeting {

    private long id;
    private String from;
    private String greeting;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", greeting='" + greeting + '\'' +
                '}';
    }
}
