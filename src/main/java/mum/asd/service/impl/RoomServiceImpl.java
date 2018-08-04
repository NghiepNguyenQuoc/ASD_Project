package mum.asd.service.impl;

import mum.asd.domain.Booking;
import mum.asd.domain.Room;
import mum.asd.repository.BookingRepository;
import mum.asd.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    BookingRepository bookingRepository;

    public List<Room> findAll(){
        return roomRepository.findAll();
    }

    public List<Room> findAvailableRoomByKeyword(String keyword){
        keyword = "%"+keyword+"%";
        return roomRepository.findAllByKeyword(keyword);
    }

    public List<Room> findAvailableRoom(){
        List<Booking> bookings = bookingRepository.findAll();
        for (Booking b: bookings){
            List<Room> rooms = b.getRooms();
            for (Room r: rooms){
                r.setRoomVailable(false);
                roomRepository.save(r);

            }
        }
        return roomRepository.findAllByStatus(true);
    }
}
