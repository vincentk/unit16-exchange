package com.unit16.exchange.api.orders;

public interface ExceptionHandler {

	static interface Response {
		
		static class UnkownOrder implements Response
		{
			@Override public String toString() { return "Unknown order."; }
		}
	}
	
	public void exception(long id, Response message);
}
