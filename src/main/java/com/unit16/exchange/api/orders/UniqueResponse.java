package com.unit16.exchange.api.orders;

import java.util.function.Consumer;

import com.unit16.exchange.api.HasId;
import com.unit16.exchange.api.orders.ExceptionHandler.Response;
import com.unit16.z.WithCopyMethod;

public interface UniqueResponse extends Response, HasId {
	public static interface Exposure extends UniqueResponse
	{
		int leavesQty();
		int limitPriceTicks();
		int lastQty();
		int lastPxTicks();
		
		static final class M
		implements Exposure, WithCopyMethod<Exposure>
		{
			public long id_;
			public int leavesQty_;
			public int limitPriceTicks_;
			public int lastQty_;
			public int lastPxTicks_;
			
			@Override public long id() { return id_; }
			@Override public int leavesQty() { return leavesQty_; }
			@Override public int limitPriceTicks() { return limitPriceTicks_; }
			@Override public int lastQty() { return lastQty_; }
			@Override public int lastPxTicks() { return lastPxTicks_; }

			@Override
			public void copyFrom(Exposure val) {
				id_ = val.id();
				leavesQty_ = val.leavesQty();
				limitPriceTicks_ = val.limitPriceTicks();
				lastQty_ = val.lastQty();
				lastPxTicks_ = val.lastPxTicks();	
			}
		}
	}
	
	public static interface Ack extends UniqueResponse
	{
		public int priceInTicks();
		public int volumeInLots();
		
		static final class M
		extends Order
		implements Ack, WithCopyMethod<Ack>
		{
			@Override public long id() { return id_; }
			@Override public int priceInTicks() { return price_; }
			@Override public int volumeInLots() { return volume_; }
			
			@Override
			public void copyFrom(Ack val) {
				id_ = val.id();
				price_ = val.priceInTicks();
				volume_ = val.volumeInLots();
			}
		}	
	}
	
	static interface Exception extends UniqueResponse
	{
		Response message();
		
		static final class M
		implements Exception, WithCopyMethod<Exception>
		{
			public long id_;
			public Response msg_;
			
			@Override public long id() { return id_; }
			@Override public Response message() { return msg_; }
			@Override
			public void copyFrom(Exception val) {
				id_ = val.id();
				msg_ = val.message();
			}
		}
	}
	
	public static final class HandlerIsContinuation implements Consumer<UniqueResponse>
	{
		private final OrderAndTradeHandler oh;
		public HandlerIsContinuation(OrderAndTradeHandler oh_) { oh = oh_; }

		@Override
		public void accept(UniqueResponse val) {

			if (val instanceof Exposure)
			{
				final Exposure.M e = (Exposure.M) val;
				oh.executionReport(e.id(), 
						e.leavesQty(), e.limitPriceTicks(), 
						e.lastQty(), e.lastPxTicks());
			}
			else if (val instanceof Ack)
			{
				final Ack.M a = (Ack.M) val;
				oh.ack(a.id(), a.priceInTicks(), a.volumeInLots());
			}
			else if (val instanceof UniqueResponse.Exception)
			{
				final UniqueResponse.Exception.M a = (UniqueResponse.Exception.M) val;
				oh.exception(a.id_, a.msg_);
			}
			else
			{
				assert false : "Should never happen.";	
			}
		}
	}
}
