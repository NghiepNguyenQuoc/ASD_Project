package mum.asd.domain.bookingprices;

import mum.asd.domain.Room;

/**
 * @author vynguyen
 *
 */
public interface ServiceElementVisitor {
	public void visit(Room room);
}
