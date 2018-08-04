package com.myhotel.domain.bookingprices;

import com.myhotel.domain.Room;

/**
 * @author vynguyen
 *
 */
public interface ServiceElementVisitor {
	public void visit(Room room);
}
