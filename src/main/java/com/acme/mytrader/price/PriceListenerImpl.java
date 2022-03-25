package com.acme.mytrader.price;

import com.acme.mytrader.exception.NotAbleToBuyException;
import com.acme.mytrader.execution.ExecutionService;

public class PriceListenerImpl implements PriceListener {
    ExecutionService executionService;
    private static final double threshold = 55.0;
    private static final String validTicker = "ibm";

    public PriceListenerImpl(ExecutionService executionService) {
        this.executionService = executionService;
    }

    public void priceUpdate(String security, double price) {
        if(validTicker.equalsIgnoreCase(security) && price <= threshold ) {
            //trigger stock purchase
            executionService.buy( security,  price, 100);
        } else {
            //do nothing
        	throw new NotAbleToBuyException();
        }
    }
}
