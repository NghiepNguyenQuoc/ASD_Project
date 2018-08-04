package mum.asd.service;

import mum.asd.domain.Card;
import mum.asd.domain.Payment;
import mum.asd.generic.GenericService;

import java.util.List;

public interface PaymentService extends GenericService<Payment> {

    public List<Card> getListCardsByPayment(Payment payment);
}
