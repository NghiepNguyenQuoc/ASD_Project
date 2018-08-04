package com.myhotel.domain.bookingprices;

import com.myhotel.domain.Room;

/**
 * @author vynguyen
 *
 */
public class ServiceElementDoVisitor implements ServiceElementVisitor {
	private double price;

	@Override
	public void visit(Room room) {
		// TODO Auto-generated method stub
		price += room.getPrice() + (room.getPrice() * room.getTax());
	}

	public double getPrice() {
		return price;
	}
}
