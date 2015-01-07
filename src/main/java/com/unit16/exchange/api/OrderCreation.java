package com.unit16.exchange.api;

public interface OrderCreation {

	public long create(int priceInTicks, int volumeInLots);
	
	public long update(long id, int priceInTicks, int volumeInLots);
	
	public void delete(long id);
}
