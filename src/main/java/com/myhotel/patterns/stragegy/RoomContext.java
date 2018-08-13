package com.myhotel.patterns.stragegy;

import java.util.List;

import com.myhotel.domain.Room;

public class RoomContext {
	private RoomFactory factory;
	private static RoomContext instance;

	public RoomContext(RoomFactory factory) {
		this.factory = factory;
	}

	public static RoomContext getInstance(RoomFactory factory) {
		if (instance == null) {
			return new RoomContext(factory);
		}
		return instance;
	}

	public RoomFactory getFactory() {
		return factory;
	}

	public void setFactory(RoomFactory factory) {
		this.factory = factory;
	}

	public long getRevenue(List<Room> rooms) {
		return factory.getRevenue(rooms);
	}
}
