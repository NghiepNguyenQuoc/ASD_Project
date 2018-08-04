package com.myhotel.domain.bookingprices;

import com.myhotel.domain.Room;

public interface ServiceElementVisitor {
	public void visit(Room room);
}
