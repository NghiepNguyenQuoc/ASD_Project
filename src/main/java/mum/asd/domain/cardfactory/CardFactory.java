package mum.asd.domain.cardfactory;

import mum.asd.domain.Card;

public interface CardFactory {
	public Card createCard(String cardType);
}
