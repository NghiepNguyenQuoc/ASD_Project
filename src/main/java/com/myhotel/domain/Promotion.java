package com.myhotel.domain;

import javax.persistence.*;

import com.myhotel.patterns.prototype.PromotionPrototype;

@Entity
public class Promotion implements PromotionPrototype {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    private String name;
    private int discount;
    private float percent;

    public Promotion() {
    }

    public Promotion(String name, int discount, float percent) {
        this.name = name;
        this.discount = discount;
        this.percent = percent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    @Override
    public Promotion doClone() {
    	Promotion promo= new Promotion();
    	promo.setDiscount(this.getDiscount());
    	promo.setPercent(this.getPercent());
    	promo.setName(this.getName());
        return promo;
    }
    
    public static Promotion samplePromo;
	static {
		samplePromo = new Promotion();
		samplePromo.setDiscount(20);
		samplePromo.setPercent(20);
		samplePromo.setName("HotelPromo");
	}
}
