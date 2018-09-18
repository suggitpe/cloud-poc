package org.suggs.cloudpoc.otherconsumer.trade;

import java.time.LocalDateTime;
import java.util.Objects;

public class TradeEvent {
    private TradeIdentifier tradeIdentifier;
    private String eventType;
    private String eventSubType;
    private LocalDateTime executionTimestamp;

    public TradeIdentifier getTradeIdentifier() {
        return tradeIdentifier;
    }

    public void setTradeIdentifier(TradeIdentifier tradeIdentifier) {
        this.tradeIdentifier = tradeIdentifier;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventSubType() {
        return eventSubType;
    }

    public void setEventSubType(String eventSubType) {
        this.eventSubType = eventSubType;
    }

    public LocalDateTime getExecutionTimestamp() {
        return executionTimestamp;
    }

    public void setExecutionTimestamp(LocalDateTime executionTimestamp) {
        this.executionTimestamp = executionTimestamp;
    }

    @Override
    public String toString() {
        return "TradeEvent{" +
                "tradeIdentifier=" + tradeIdentifier +
                ", eventType='" + eventType + '\'' +
                ", eventSubType='" + eventSubType + '\'' +
                ", executionTimestamp=" + executionTimestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeEvent that = (TradeEvent) o;
        return Objects.equals(tradeIdentifier, that.tradeIdentifier) &&
                Objects.equals(eventType, that.eventType) &&
                Objects.equals(eventSubType, that.eventSubType) &&
                Objects.equals(executionTimestamp, that.executionTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeIdentifier, eventType, eventSubType, executionTimestamp);
    }
}
