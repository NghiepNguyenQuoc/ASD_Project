package com.myhotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhotel.domain.Promotion;
import com.myhotel.repository.PromotionRepository;
import com.myhotel.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	PromotionRepository roomRepository;

	///////////
	@Override
	public Promotion save(Promotion entity) {
		// TODO Auto-generated method stub
		return roomRepository.save(entity);
	}

	@Override
	public Promotion update(Promotion entity) {
		// TODO Auto-generated method stub
		return roomRepository.save(entity);
	}

	@Override
	public void delete(Promotion entity) {
		// TODO Auto-generated method stub
		roomRepository.delete(entity);
	}

	@Override
	public void deleteInBatch(List<Promotion> entities) {
		// TODO Auto-generated method stub
		roomRepository.deleteInBatch(entities);
	}
	////////

	public List<Promotion> findAll() {
		return roomRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Promotion find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
