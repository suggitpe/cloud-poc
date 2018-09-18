package org.suggs.cloudpoc.producer.trade;

import org.suggs.cloudpoc.producer.trade.domain.TradeEvent;
import org.suggs.cloudpoc.producer.trade.domain.TradeEventIdentifier;
import org.suggs.cloudpoc.producer.trade.domain.TradeEventLeg;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TradeEventRepository {

    private Map<TradeEventIdentifier, TradeEvent> tradeStore = new HashMap<>();

    public static TradeEventRepository init() {
        TradeEventRepository tradeRepo = new TradeEventRepository();
        tradeRepo.addTradeEvent(createTradeEvent1());
        tradeRepo.addTradeEvent(createTradeEvent2());
        tradeRepo.addTradeEvent(createTradeEvent3());
        tradeRepo.addTradeEvent(createTradeEvent4());
        return tradeRepo;
    }

    public TradeEvent getTradeBy(TradeEventIdentifier tradeIdentifier) {
        return tradeStore.get(tradeIdentifier);
    }

    private void addTradeEvent(TradeEvent event) {
        tradeStore.put(event.getTradeIdentifier(), event);
    }

    private static TradeEvent createTradeEvent1() {
        TradeEventIdentifier id = new TradeEventIdentifier("1", "testDomain", 1);
        TradeEventLeg leg1 = new TradeEventLeg("Buyer", "GBP", 12000);
        TradeEventLeg leg2 = new TradeEventLeg("Seller", "USD", 34000);
        return new TradeEvent("New", "New", id, LocalDateTime.parse("2018-09-18T07:50:09.073"), Arrays.asList(leg1, leg2));
    }

    private static TradeEvent createTradeEvent2() {
        TradeEventIdentifier id = new TradeEventIdentifier("1", "testDomain", 2);
        TradeEventLeg leg1 = new TradeEventLeg("Buyer", "GBP", 24000);
        TradeEventLeg leg2 = new TradeEventLeg("Seller", "USD", 34000);
        return new TradeEvent("New", "Amend", id, LocalDateTime.parse("2018-09-18T07:50:09.073"), Arrays.asList(leg1, leg2));
    }

    private static TradeEvent createTradeEvent3() {
        TradeEventIdentifier id = new TradeEventIdentifier("2", "testDomain", 6);
        TradeEventLeg leg1 = new TradeEventLeg("Buyer", "AUD", 12000);
        TradeEventLeg leg2 = new TradeEventLeg("Seller", "JPY", 34000);
        return new TradeEvent("Termination", "Amend", id, LocalDateTime.parse("2018-09-18T07:50:09.073"), Arrays.asList(leg1, leg2));
    }

    private static TradeEvent createTradeEvent4() {
        TradeEventIdentifier id = new TradeEventIdentifier("3", "testDomain", 45);
        TradeEventLeg leg1 = new TradeEventLeg("Buyer", "EUR", 12000);
        TradeEventLeg leg2 = new TradeEventLeg("Seller", "ZLT", 34000);
        return new TradeEvent("Novation", "New", id, LocalDateTime.parse("2018-09-18T07:50:09.073"), Arrays.asList(leg1, leg2));
    }
}
