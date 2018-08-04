package com.myhotel.domain.cardfactory;

import com.myhotel.domain.Card;

public interface CardFactory {
	public Card createCard(String cardType);
}
