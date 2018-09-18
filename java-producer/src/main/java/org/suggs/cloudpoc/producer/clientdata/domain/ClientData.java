package org.suggs.cloudpoc.producer.clientdata.domain;

public class ClientData {
    private final long id;
    private final String name;
    private final String secretCode;
    private final String location;

    public ClientData(long id, String name, String secretCode, String location) {
        this.id = id;
        this.name = name;
        this.secretCode = secretCode;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public String getLocation() {
        return location;
    }
}
