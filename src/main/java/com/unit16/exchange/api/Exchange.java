package com.unit16.exchange.api;

import com.unit16.exchange.api.market.MarketUpdate;
import com.unit16.exchange.api.orders.OrderAndTradeHandler;

public interface Exchange {

	public enum Status implements MarketUpdate
	{
		TRADING,
		NOT_TRADING,
		UNKNOWN
	}

	public Status status(String product);
	
	/**
	 * Feed:
	 */
	public void subscribe(String product, FeedHandler handler) throws UnknownProductException;
	public void unsubscribe(FeedHandler handler);
	
	/**
	 * Orders and trades:
	 */
	public OrderCreation orderCreation(String product) throws UnknownProductException;
	
	public void subscribe(String product, OrderAndTradeHandler handler) throws UnknownProductException;
	public void unsubscribe(OrderAndTradeHandler handler);
	
	public static final class UnknownProductException extends Exception
	{
		private static final long serialVersionUID = 1L;

		public UnknownProductException(String product)
		{
			super("Unknown product: " + product);
		}
	}
}
