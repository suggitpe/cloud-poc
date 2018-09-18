package org.suggs.cloudpoc.consumer.trade.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeEvent {

    private TradeEventIdentifier tradeIdentifier;
    private String eventType;
    private LocalDateTime executionTimestamp;
    private List<TradeEventLeg> legs;

    public TradeEventIdentifier getTradeIdentifier() {
        return tradeIdentifier;
    }

    public void setTradeIdentifier(TradeEventIdentifier tradeIdentifier) {
        this.tradeIdentifier = tradeIdentifier;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getExecutionTimestamp() {
        return executionTimestamp;
    }

    public void setExecutionTimestamp(LocalDateTime executionTimestamp) {
        this.executionTimestamp = executionTimestamp;
    }

    public List<TradeEventLeg> getLegs() {
        return legs;
    }

    public void setLegs(List<TradeEventLeg> legs) {
        this.legs = legs;
    }

    @Override
    public String toString() {
        return "TradeEvent{" +
                "tradeIdentifier=" + tradeIdentifier +
                ", eventType='" + eventType + '\'' +
                ", executionTimestamp=" + executionTimestamp +
                ", legs=" + legs +
                '}';
    }
}
