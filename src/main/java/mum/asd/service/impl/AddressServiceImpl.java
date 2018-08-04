package mum.asd.service.impl;

import mum.asd.domain.Address;
import mum.asd.domain.Booking;
import mum.asd.repository.AddressRepository;
import mum.asd.repository.BookingRepository;
import mum.asd.service.AddressService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
