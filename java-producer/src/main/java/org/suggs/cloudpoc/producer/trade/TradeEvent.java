package org.suggs.cloudpoc.producer.trade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class TradeEvent {

    private final TradeIdentifier tradeIdentifier;
    private final String eventType;
    private final String eventSubType;
    private final LocalDateTime executionTimestamp;
    private final List<TradeLeg> legs;
    private final boolean fieldThatNoOneUses = false;

    public TradeEvent(String eventType, String eventSubType, TradeIdentifier tradeIdentifier, LocalDateTime executionTimestamp, List<TradeLeg> legs) {
        this.eventType = eventType;
        this.eventSubType = eventSubType;
        this.tradeIdentifier = tradeIdentifier;
        this.executionTimestamp = executionTimestamp;
        this.legs = legs;
    }

    public TradeIdentifier getTradeIdentifier() {
        return tradeIdentifier;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventSubType() {
        return eventSubType;
    }

    public LocalDateTime getExecutionTimestamp() {
        return executionTimestamp;
    }

    public List<TradeLeg> getLegs() {
        return legs;
    }

    public boolean isFieldThatNoOneUses() {
        return fieldThatNoOneUses;
    }

    @Override
    public String toString() {
        return "TradeEvent{" +
                "tradeIdentifier=" + tradeIdentifier +
                ", eventType='" + eventType + '\'' +
                ", eventSubType='" + eventSubType + '\'' +
                ", executionTimestamp=" + executionTimestamp +
                ", legs=" + legs +
                ", fieldThatNoOneUses=" + fieldThatNoOneUses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeEvent that = (TradeEvent) o;
        return fieldThatNoOneUses == that.fieldThatNoOneUses &&
                Objects.equals(tradeIdentifier, that.tradeIdentifier) &&
                Objects.equals(eventType, that.eventType) &&
                Objects.equals(eventSubType, that.eventSubType) &&
                Objects.equals(executionTimestamp, that.executionTimestamp) &&
                Objects.equals(legs, that.legs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeIdentifier, eventType, eventSubType, executionTimestamp, legs, fieldThatNoOneUses);
    }
}
