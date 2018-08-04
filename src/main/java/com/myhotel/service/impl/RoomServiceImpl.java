package com.myhotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhotel.domain.Booking;
import com.myhotel.domain.Room;
import com.myhotel.repository.BookingRepository;
import com.myhotel.repository.RoomRepository;

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
