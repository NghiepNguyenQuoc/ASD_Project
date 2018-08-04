package com.myhotel.service;

import java.util.List;

import com.myhotel.domain.Card;
import com.myhotel.domain.Payment;
import com.myhotel.generic.GenericService;

public interface PaymentService extends GenericService<Payment> {

    public List<Card> getListCardsByPayment(Payment payment);
}
