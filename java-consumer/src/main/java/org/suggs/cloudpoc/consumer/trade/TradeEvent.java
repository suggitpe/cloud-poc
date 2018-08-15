package org.suggs.cloudpoc.consumer.trade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeEvent {

    private String eventType;
    private String eventSubType;
    private TradeIdentifier tradeIdentifier;
    private LocalDateTime executionTimestamp;
    private List<TradeLeg> legs;

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

    public TradeIdentifier getTradeIdentifier() {
        return tradeIdentifier;
    }

    public void setTradeIdentifier(TradeIdentifier tradeIdentifier) {
        this.tradeIdentifier = tradeIdentifier;
    }

    public LocalDateTime getExecutionTimestamp() {
        return executionTimestamp;
    }

    public void setExecutionTimestamp(LocalDateTime executionTimestamp) {
        this.executionTimestamp = executionTimestamp;
    }

    public List<TradeLeg> getLegs() {
        return legs;
    }

    public void setLegs(List<TradeLeg> legs) {
        this.legs = legs;
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
