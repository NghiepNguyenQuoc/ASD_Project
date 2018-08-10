package com.myhotel.patterns.Mediator;

import com.myhotel.domain.HotelUser;
import com.myhotel.domain.Promotion;
import com.myhotel.patterns.FactoryMethod.ConcretePromotionFactory;
import com.myhotel.patterns.FactoryMethod.HolidayPromotion;
import com.myhotel.patterns.Singleton.PromotionRepositorySingleton;
import com.myhotel.repository.HotelUserRepository;
import com.myhotel.repository.PromotionRepository;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.CardService;
import com.myhotel.service.HotelUserService;
public class ConcreteHotelCustomer extends HotelCustomer {
    public ConcreteHotelCustomer(PromotionMediator promotionMediator, String promotionName, HotelUser hotelUser) {
        super(promotionMediator, promotionName, hotelUser);
    }

    public ConcreteHotelCustomer(PromotionMediator promotionMediator, HotelUser hotelUser){
        super(promotionMediator,null,hotelUser);
    }

    @Override
    public void sendPromotion(String promotionName) {
        promotionMediator.broadCastPromotion(promotionName,this);

    }

    @Override
    public void receivePromotion(String promotionName) {
        HolidayPromotion holidayPromotion = new ConcretePromotionFactory().createPromotion(promotionName);
        Promotion promotion = holidayPromotion.getPromotion();
        HotelUserService hotelUserService = ApplicationContextHolder.getContext().getBean(HotelUserService.class);
        HotelUser hotelUser = hotelUserService.find(this.hotelUser.getId());
        hotelUser.getPromotions().add(promotion);
        PromotionRepository promotionRepository = PromotionRepositorySingleton.INSTANCE.getPromotionRepository();
        promotionRepository.save(promotion);
        HotelUserRepository hotelUserRepository = ApplicationContextHolder.getContext().getBean(HotelUserRepository.class);
        hotelUserRepository.save(hotelUser);
    }
}
