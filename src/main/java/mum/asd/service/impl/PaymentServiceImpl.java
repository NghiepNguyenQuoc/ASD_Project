package mum.asd.service.impl;

import java.util.List;

import mum.asd.domain.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.asd.domain.Payment;
import mum.asd.repository.PaymentRepository;
import mum.asd.service.PaymentService;

import javax.transaction.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<Card>  getListCardsByPayment(Payment payment){
		Payment payment1 = paymentRepository.findOne(payment.getId());
		payment1.getCards().size();
		return payment.getCards();
	}


	@Override
	public Payment save(Payment entity) {
		// TODO Auto-generated method stub
		return paymentRepository.save(entity);
	}

	@Override
	public Payment update(Payment entity) {
		// TODO Auto-generated method stub
		return paymentRepository.save(entity);
	}

	@Override
	public void delete(Payment entity) {
		// TODO Auto-generated method stub
		paymentRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		paymentRepository.delete(id);
	}

	@Override
	public void deleteInBatch(List<Payment> entities) {
		// TODO Auto-generated method stub
		paymentRepository.deleteInBatch(entities);
	}

	@Override
	public Payment find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
