package com.unit16.exchange.api.orders;

public interface AckHandler {

	public void ack(long id, int priceInTicks, int volumeInLots);

}
