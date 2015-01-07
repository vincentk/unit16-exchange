package com.unit16.exchange.api;

import com.unit16.exchange.api.Exchange.Status;
import com.unit16.exchange.api.market.Quote;

public interface FeedHandler extends TradeHandler {
	
	public void quote(Quote q);
	public void statusChange(Status s);
}
