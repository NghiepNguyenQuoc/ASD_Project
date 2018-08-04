package com.myhotel.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;  

    private String type;
    private float amount;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Card> cards = new ArrayList<>();
    
    public Payment() {
    	
    }

    public Payment(String type, float amount) {
		super();
		this.type = type;
		this.amount = amount;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    
    public void addCard(Card card) {
    	this.cards.add(card);
    }
}
