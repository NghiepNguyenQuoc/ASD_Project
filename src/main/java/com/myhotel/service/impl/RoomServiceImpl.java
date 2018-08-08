package com.myhotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.myhotel.domain.Booking;
import com.myhotel.domain.Room;
import com.myhotel.repository.BookingRepository;
import com.myhotel.repository.RoomRepository;
import com.myhotel.service.BookingService;
import com.myhotel.service.RoomService;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    BookingRepository bookingRepository;

    ///////////
	@Override
	public Room save(Room entity) {
		// TODO Auto-generated method stub
		return roomRepository.save(entity);
	}

	@Override
	public Room update(Room entity) {
		// TODO Auto-generated method stub
		return roomRepository.save(entity);
	}

	@Override
	public void delete(Room entity) {
		// TODO Auto-generated method stub
		roomRepository.delete(entity);
	}

	@Override
	public void deleteInBatch(List<Room> entities) {
		// TODO Auto-generated method stub
		roomRepository.deleteInBatch(entities);
	}
    ////////
    
    public List<Room> findAll(){
        return roomRepository.findAll();
    }

    public List<Room> findAvailableRoomByKeyword(String keyword){
        keyword = "%"+keyword+"%";
        return roomRepository.findAllByKeyword(keyword);
    }

    public List<Room> findAvailableRoom(){
        List<Booking> bookings = bookingRepository.findAll();
        bookings.removeIf(b -> (b.getCheckOutStatus()==null? false : b.getCheckOutStatus())==true);
        for (Booking b: bookings){
            List<Room> rooms = b.getRooms();
            for (Room r: rooms){
                r.setRoomVailable(false);
                roomRepository.save(r);

            }
        }
        return roomRepository.findAllByStatus(true);
    }

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Room find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}

