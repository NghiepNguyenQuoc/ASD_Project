package com.myhotel.patterns.Iterator;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.myhotel.domain.Room;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.impl.RoomServiceImpl;

public class RoomRepository implements RoomContainer {

	List<Room> rooms = null;
	@Autowired
	RoomServiceImpl roomService;

	@Override
	public Iterator getIterator(String searchKeywork) {
		if (roomService == null) {
			roomService = ApplicationContextHolder.getContext().getBean(RoomServiceImpl.class);
		}

		if (searchKeywork == null || searchKeywork.equals("")) {
			rooms = roomService.findAvailableRoom();
		} else {
			rooms = roomService.findAvailableRoomByKeyword(searchKeywork);
		}
		return new RoomIterator();
	}

	private class RoomIterator implements Iterator {

		int index;

		@Override
		public boolean hasNext() {
			if (index < rooms.size()) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if(this.hasNext()){
	            return rooms.get(index++);
	         }
	         return null;
		}

		@Override
		public boolean hasPrev() {
			if (index > 0) {
				return true;
			}
			return false;
		}

		@Override
		public Object previous() {
			if(this.hasPrev()){
	            return rooms.get(index--);
	         }
	         return null;
		}

	}

}
