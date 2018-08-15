package org.suggs.cloudpoc.producer.trade;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TradeRepository {

    private Map<TradeIdentifier, TradeEvent> tradeStore = new HashMap<>();

    public static TradeRepository init() {
        TradeRepository tradeRepo = new TradeRepository();
        tradeRepo.addTradeEvent(createTradeEvent1());
        tradeRepo.addTradeEvent(createTradeEvent2());
        tradeRepo.addTradeEvent(createTradeEvent3());
        tradeRepo.addTradeEvent(createTradeEvent4());
        return tradeRepo;
    }

    public TradeEvent getTradeBy(TradeIdentifier tradeIdentifier) {
        return tradeStore.get(tradeIdentifier);
    }

    private void addTradeEvent(TradeEvent event) {
        tradeStore.put(event.getTradeIdentifier(), event);
    }

    private static TradeEvent createTradeEvent1() {
        TradeIdentifier id = new TradeIdentifier("1", "testDomain", 1);
        TradeLeg leg1 = new TradeLeg("Buyer", "GBP", 12000);
        TradeLeg leg2 = new TradeLeg("Seller", "USD", 34000);
        return new TradeEvent("New", "New", id, DateTime.now(), Arrays.asList(leg1, leg2));
    }

    private static TradeEvent createTradeEvent2() {
        TradeIdentifier id = new TradeIdentifier("1", "testDomain", 2);
        TradeLeg leg1 = new TradeLeg("Buyer", "GBP", 24000);
        TradeLeg leg2 = new TradeLeg("Seller", "USD", 34000);
        return new TradeEvent("New", "Amend", id, DateTime.now(), Arrays.asList(leg1, leg2));
    }

    private static TradeEvent createTradeEvent3() {
        TradeIdentifier id = new TradeIdentifier("2", "testDomain", 6);
        TradeLeg leg1 = new TradeLeg("Buyer", "AUD", 12000);
        TradeLeg leg2 = new TradeLeg("Seller", "JPY", 34000);
        return new TradeEvent("Termination", "Amend", id, DateTime.now(), Arrays.asList(leg1, leg2));
    }

    private static TradeEvent createTradeEvent4() {
        TradeIdentifier id = new TradeIdentifier("3", "testDomain", 45);
        TradeLeg leg1 = new TradeLeg("Buyer", "EUR", 12000);
        TradeLeg leg2 = new TradeLeg("Seller", "ZLT", 34000);
        return new TradeEvent("Novation", "New", id, DateTime.now(), Arrays.asList(leg1, leg2));
    }

}
