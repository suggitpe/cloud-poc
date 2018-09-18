package org.suggs.cloudpoc.otherconsumer.trade;

import java.util.Objects;

public class TradeIdentifier {
    private String id;
    private String domain;
    private long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeIdentifier that = (TradeIdentifier) o;
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
        return "TradeIdentifier{" +
                "id='" + id + '\'' +
                ", domain='" + domain + '\'' +
                ", version=" + version +
                '}';
    }
}
