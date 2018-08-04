package com.myhotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhotel.domain.Address;
import com.myhotel.domain.Booking;
import com.myhotel.repository.AddressRepository;
import com.myhotel.repository.BookingRepository;
import com.myhotel.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressRepository addressRepository;

	@Override
	public Address save(Address entity) {
		// TODO Auto-generated method stub
		return addressRepository.save(entity);
	}

	@Override
	public Address update(Address entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Address entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(List<Address> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Address find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
