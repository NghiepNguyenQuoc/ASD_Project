package com.myhotel.domain.cardfactory;

import com.myhotel.domain.Card;
import com.myhotel.domain.VisaCard;
import com.myhotel.domain.MasterCard;

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
			case "Visa":
				///validating by Visa type
				card = new Card();
				break;
			case "Master":
				///validating by Master type
				card = new Card();
				break;
			case "Discover":
				///validating by Discover type
				card = new Card();
				break;
		}
		return card;
	}

}
