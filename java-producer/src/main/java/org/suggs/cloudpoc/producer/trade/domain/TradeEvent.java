package org.suggs.cloudpoc.producer.trade.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class TradeEvent {

    private final TradeEventIdentifier tradeIdentifier;
    private final String eventType;
    private final String eventSubType;
    private final LocalDateTime executionTimestamp;
    private final List<TradeEventLeg> legs;
    private final boolean fieldThatNoOneUses = false;

    public TradeEvent(String eventType, String eventSubType, TradeEventIdentifier tradeIdentifier, LocalDateTime executionTimestamp, List<TradeEventLeg> legs) {
        this.eventType = eventType;
        this.eventSubType = eventSubType;
        this.tradeIdentifier = tradeIdentifier;
        this.executionTimestamp = executionTimestamp;
        this.legs = legs;
    }

    public TradeEventIdentifier getTradeIdentifier() {
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

    public List<TradeEventLeg> getLegs() {
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
