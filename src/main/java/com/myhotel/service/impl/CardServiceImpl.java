package com.myhotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhotel.domain.Card;
import com.myhotel.repository.CardRepository;
import com.myhotel.service.CardService;

import javax.transaction.Transactional;

@Service
@Transactional
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardRepository cardRepository;

	@Override
	public Card save(Card entity) {
		// TODO Auto-generated method stub
		return cardRepository.save(entity);
	}

	@Override
	public Card update(Card entity) {
		// TODO Auto-generated method stub
		return cardRepository.save(entity);
	}

	@Override
	public void delete(Card entity) {
		// TODO Auto-generated method stub
		cardRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		cardRepository.delete(id);
	}

	@Override
	public void deleteInBatch(List<Card> entities) {
		// TODO Auto-generated method stub
		cardRepository.deleteInBatch(entities);
	}

	@Override
	public Card find(Long id) {
		// TODO Auto-generated method stub
		return cardRepository.findOne(id);
	}

	@Override
	public List<Card> findAll() {
		// TODO Auto-generated method stub
		return cardRepository.findAll();
	}

}
