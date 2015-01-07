package com.unit16.exchange.api.market;

import com.unit16.z.WithCopyMethod;


public interface Trade extends MarketUpdate {

	public int priceInTicks();
	public int volumeInLots();
	
	public static final class M
	implements Trade, WithCopyMethod<Trade>
	{
		public int price_;
		public int volume_;
		
		public M(){}
		
		public M(int priceInTicks, int volumeInLots) { 
			price_ = priceInTicks; volume_ = volumeInLots; 
		}

		@Override
		public int priceInTicks() { return price_; }

		@Override
		public int volumeInLots() { return volume_; }
		
		@Override
		public String toString()
		{
			return "T:" + volumeInLots() + "@" + priceInTicks(); 
		}

		@Override
		public void copyFrom(Trade val) {
			price_ = val.priceInTicks();
			volume_ = val.volumeInLots();
		}
	}
}
