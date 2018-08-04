package com.myhotel.domain.cardfactory;

import com.myhotel.domain.Card;
import com.myhotel.domain.CreditCard;
import com.myhotel.domain.DebitCard;

public class SimpleCardFactory implements CardFactory {
	private static CardFactory factory = new SimpleCardFactory();
	private SimpleCardFactory(){}
	public static CardFactory getFactory() { 
		return factory;
	}

	@Override
	public Card createCard(String cardType) {
		Card card = null;
		
		switch (cardType) {
			case "Normal":
				card = new Card();
				break;
			case "Debit":
				card = new DebitCard();
				break;
			case "Credit":
				card = new CreditCard();
				break;
		}
		return card;
	}

}
