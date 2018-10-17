package org.suggs.cloudpoc.producer.trade.domain;

import java.util.Objects;

public class TradeEventIdentifier {

    private final long id;
    private final String domain;
    private final long version;

    public TradeEventIdentifier(long id, String domain, long version) {
        this.id = id;
        this.domain = domain;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public String getDomain() {
        return domain;
    }

    public long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeEventIdentifier that = (TradeEventIdentifier) o;
        return version == that.version &&
                Objects.equals(id, that.id) &&
                Objects.equals(domain, that.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, domain, version);
    }

    @Override
    public String toString() {
        return "TradeEventIdentifier{" +
                "id='" + id + '\'' +
                ", domain='" + domain + '\'' +
                ", version=" + version +
                '}';
    }
}
