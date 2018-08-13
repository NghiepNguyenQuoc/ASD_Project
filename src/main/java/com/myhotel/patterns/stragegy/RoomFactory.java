package com.myhotel.patterns.stragegy;

import java.util.List;

import com.myhotel.domain.Room;

public interface RoomFactory {
	public long getRevenue(List<Room> rooms);
}
