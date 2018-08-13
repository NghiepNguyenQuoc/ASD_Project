package com.myhotel.patterns.stragegy;

import java.util.List;

import com.myhotel.domain.Room;

public class StandardRoom implements RoomFactory {

	@Override
	public long getRevenue(List<Room> rooms) {
		// TODO Auto-generated method stub
		long total = 0;
		for (Room room : rooms) {
			total += (long) (room.getPrice() + room.getPrice() * (room.getTax() / 100));
		}
		return total;
	}

}
