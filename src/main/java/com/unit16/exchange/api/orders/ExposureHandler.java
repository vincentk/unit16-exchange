package com.unit16.exchange.api.orders;

public interface ExposureHandler {

	public abstract void executionReport(long id, int leavesQty,
			int limitPriceInTicks, int lastQty, int lastPxInTicks);

}