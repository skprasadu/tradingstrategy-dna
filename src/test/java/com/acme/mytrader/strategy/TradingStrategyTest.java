package com.acme.mytrader.strategy;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acme.mytrader.exception.NotAbleToBuyException;
import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceListenerImpl;

public class TradingStrategyTest {
    TradingStrategy tradingStrategy;
    PriceListener priceListener;
    
    @Mock
    ExecutionService executionService;

    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    	priceListener = spy(new PriceListenerImpl(executionService));
    	tradingStrategy = new TradingStrategy(priceListener);
    }

    @Test(expected = NotAbleToBuyException.class)
    public void executeStrategyForHigherPrice() {
        //this will test do nothing due to priced
        //We can use Mockito Spy to make sure it is going thru right condition
    	tradingStrategy.executeStrategy("IBM", 100);
    }

    @Test
    public void executeStrategyForLowerPriceThisIsPositive() {
        //this will run execute test
        //We can use Mockito Spy to make sure it is going thru right condition
    	tradingStrategy.executeStrategy("IBM", 10.0);
    	verify(priceListener).priceUpdate("IBM", 10.0);
    }

    @Test(expected = NotAbleToBuyException.class)
    public void executeStrategyForLowerPriceDifferentTicker() {
        //this will test do nothing 
        //We can use Mockito Spy to make sure it is going thru right condition
    	tradingStrategy.executeStrategy("MSFT", 10);
    }
}

