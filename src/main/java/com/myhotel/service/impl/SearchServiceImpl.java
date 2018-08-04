package com.myhotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhotel.domain.HotelUser;
import com.myhotel.repository.HotelUserRepository;

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
