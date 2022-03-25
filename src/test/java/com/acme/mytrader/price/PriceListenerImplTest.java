package com.acme.mytrader.price;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acme.mytrader.exception.NotAbleToBuyException;
import com.acme.mytrader.execution.ExecutionService;

public class PriceListenerImplTest {
	PriceListener priceListener;
	
	@Mock
	ExecutionService executionService;	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		priceListener = new PriceListenerImpl(executionService);
	}

	@Test
	public void testPriceUpdatePositiveFlow() {
		priceListener.priceUpdate("ibm", 54.0);
		
		verify(executionService).buy("ibm", 54.0, 100);
	}
	
	@Test(expected = NotAbleToBuyException.class)
	public void testPriceUpdateNegativeFlow() {
		priceListener.priceUpdate("ibm", 154.0);
	}

}
