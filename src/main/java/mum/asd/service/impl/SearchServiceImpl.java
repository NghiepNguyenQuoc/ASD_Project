package mum.asd.service.impl;

import mum.asd.domain.HotelUser;
import mum.asd.repository.HotelUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl {

    @Autowired
    HotelUserRepository hotelUserRepository;

//    public List<HotelUser> queryHoteluserByName(String name){
//        List<HotelUser> hotelUsers = hotelUserRepository.findHotelUserByFirstName(name);
//
//        return hotelUsers;
//    }
}
