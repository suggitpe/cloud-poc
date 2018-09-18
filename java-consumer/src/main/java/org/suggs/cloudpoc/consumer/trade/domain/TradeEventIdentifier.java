package org.suggs.cloudpoc.consumer.trade.domain;

public class TradeEventIdentifier {

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
    public String toString() {
        return "TradeEventIdentifier{" +
                "id='" + id + '\'' +
                ", domain='" + domain + '\'' +
                ", version=" + version +
                '}';
    }
}
