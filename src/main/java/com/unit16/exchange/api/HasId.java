package com.unit16.exchange.api;

import java.util.Comparator;

public interface HasId {

	long id();

	public static class M implements HasId
	{
		public long id_;
		
		@Override public long id() { return id_; }
	}
	
	public static final Comparator<HasId> ORDERING = new Comparator<HasId>(){
		@Override public int compare(HasId o1, HasId o2) { 
			return Long.signum(o1.id() - o2.id());
		}
	};
}
