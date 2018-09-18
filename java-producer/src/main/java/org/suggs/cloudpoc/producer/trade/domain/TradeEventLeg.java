package org.suggs.cloudpoc.producer.trade.domain;

import java.util.Objects;

public class TradeEventLeg {

    private final String legRole;
    private final String currency;
    private final long amount;

    public TradeEventLeg(String legRole, String currency, long amount) {
        this.legRole = legRole;
        this.currency = currency;
        this.amount = amount;
    }

    public String getLegRole() {
        return legRole;
    }

    public String getCurrency() {
        return currency;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeEventLeg tradeLeg = (TradeEventLeg) o;
        return amount == tradeLeg.amount &&
                Objects.equals(legRole, tradeLeg.legRole) &&
                Objects.equals(currency, tradeLeg.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(legRole, currency, amount);
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
