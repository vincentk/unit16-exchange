package com.unit16.exchange.api.orders;

import com.unit16.exchange.api.HasId;

public class Order extends HasId.M {

	public int price_;
	public int volume_;
	
	@Override
	public String toString()
	{
		return "Order(" + id_ + "):" + volume_ + "@" + price_; 
	}
	
	public static final class Create
	extends Order implements Request
	{}
	
	public static final class Update
	extends Order implements Request
	{
		public long oldId_;
	}	
	
	public static final class Delete extends HasId.M implements Request
	{}
}
