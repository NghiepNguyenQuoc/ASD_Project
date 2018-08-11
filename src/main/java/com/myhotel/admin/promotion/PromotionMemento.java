package com.myhotel.admin.promotion;

import java.io.Serializable;

/**
 * 
 * @author Cong Khanh Tran - trancongkhanh@gmail.com
 *
 *
 */
public class PromotionMemento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private int discount;
    private float percent;
    private String description;
    
	public PromotionMemento(String name, int discount, float percent, String description) {
		super();
		this.name = name;
		this.discount = discount;
		this.percent = percent;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "PromotionMemento [name=" + name + ", discount=" + discount + ", percent=" + percent + ", description="
				+ description + "]";
	}
}
