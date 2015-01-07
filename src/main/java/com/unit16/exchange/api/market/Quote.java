package com.unit16.exchange.api.market;

import com.unit16.z.WithCopyMethod;


public interface Quote extends MarketUpdate {
	public int bidP();
	public int askP();
	public int bidV();
	public int askV();
	
	public static class M implements Quote, WithCopyMethod<Quote>
	{
		public int bidP_;
		public int askP_;
		public int bidV_;
		public int askV_;
		
		@Override public final int bidP() { return bidP_; }
		
		@Override public final int askP() { return askP_; }
		
		@Override public final int bidV() { return bidV_; }
		
		@Override public final int askV() { return askV_; }
		
		@Override
		public final void copyFrom(Quote q)
		{
			bidP_ = q.bidP();
			askP_ = q.askP();
			bidV_ = q.bidV();
			askV_ = q.askV();
		}
		
		@Override
		public String toString()
		{
			return "Q:" + bidV_ + "@" + bidP_ + ":" + askV_ + "@" + askP_;
		}
		
		public boolean equals(Quote other)
		{
			return 
					bidP_ == other.bidP()
					&&
					askP_ == other.askP()
					&&
					bidV_ == other.bidV()
					&&
					askV_ == other.askV();
		}
	}
}
