package com.myhotel.patterns.Mediator;

import com.myhotel.domain.HotelUser;

public abstract class HotelCustomer {
    protected PromotionMediator promotionMediator;
    protected String promotionName;
    protected HotelUser hotelUser;

    public HotelCustomer(PromotionMediator promotionMediator, String promotionName, HotelUser hotelUser){
        this.promotionMediator = promotionMediator;
        this.promotionName = promotionName;
        this.hotelUser = hotelUser;
    }

    public abstract void sendPromotion(String promotionName);
    public abstract void receivePromotion(String promotionName);
}
