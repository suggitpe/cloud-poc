package org.suggs.cloudpoc.producer.trade;

import org.joda.time.DateTime;

import java.util.List;
import java.util.Objects;

public class TradeEvent {

    private final String eventType;
    private final String eventSubType;
    private final TradeIdentifier tradeIdentifier;
    private final DateTime executionTimestamp;
    private final List<TradeLeg> legs;

    public TradeEvent(String eventType, String eventSubType, TradeIdentifier tradeIdentifier, DateTime executionTimestamp, List<TradeLeg> legs) {
        this.eventType = eventType;
        this.eventSubType = eventSubType;
        this.tradeIdentifier = tradeIdentifier;
        this.executionTimestamp = executionTimestamp;
        this.legs = legs;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventSubType() {
        return eventSubType;
    }

    public TradeIdentifier getTradeIdentifier() {
        return tradeIdentifier;
    }

    public DateTime getExecutionTimestamp() {
        return executionTimestamp;
    }

    public List<TradeLeg> getLegs() {
        return legs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeEvent that = (TradeEvent) o;
        return Objects.equals(eventType, that.eventType) &&
                Objects.equals(eventSubType, that.eventSubType) &&
                Objects.equals(tradeIdentifier, that.tradeIdentifier) &&
                Objects.equals(executionTimestamp, that.executionTimestamp) &&
                Objects.equals(legs, that.legs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType, eventSubType, tradeIdentifier, executionTimestamp, legs);
    }

    @Override
    public String toString() {
        return "TradeEvent{" +
                "eventType='" + eventType + '\'' +
                ", eventSubType='" + eventSubType + '\'' +
                ", tradeIdentifier=" + tradeIdentifier +
                ", executionTimestamp=" + executionTimestamp +
                ", legs=" + legs +
                '}';
    }
}
