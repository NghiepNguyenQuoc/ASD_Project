package com.myhotel.patterns.stragegy;

import java.util.List;

import com.myhotel.domain.Room;


public class SuiteRoom implements RoomFactory {

	@Override
	public long getRevenue(List<Room> rooms) {
		long total = 0;
		for (Room room : rooms) {
//			every adult: +100
//			every child: +20
//			surcharge: +500
			long preTax = (long) (room.getPrice() + room.getNumberAdult() * 50 + room.getNumberChildren() * 10 + 500);
			total += (preTax + preTax * (room.getTax() / 100));
		}
		return total;
	}
}
