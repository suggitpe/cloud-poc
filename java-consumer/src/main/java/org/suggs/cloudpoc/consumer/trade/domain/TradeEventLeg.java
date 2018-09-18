package org.suggs.cloudpoc.consumer.trade.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeEventLeg {

    private String legRole;
    private String currency;
    private long amount;

    public String getLegRole() {
        return legRole;
    }

    public void setLegRole(String legRole) {
        this.legRole = legRole;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TradeEventLeg{" +
                "legRole='" + legRole + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }
}
