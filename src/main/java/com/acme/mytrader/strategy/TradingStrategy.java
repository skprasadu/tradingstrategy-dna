package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceListener;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {
    PriceListener priceListener;

    public TradingStrategy(PriceListener priceListener) {
    	this.priceListener = priceListener;
    }

    void executeStrategy(String security, double price) {
        priceListener.priceUpdate( security, price);
    }
}
