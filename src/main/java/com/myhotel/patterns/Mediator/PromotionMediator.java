package com.myhotel.patterns.Mediator;

public interface PromotionMediator {
    public void broadCastPromotion(String promotionName, HotelCustomer hotelCustomer);
    public void addHotelCustomer(HotelCustomer hotelCustomer);
}
