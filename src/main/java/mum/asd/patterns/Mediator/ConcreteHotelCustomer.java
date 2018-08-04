package mum.asd.patterns.Mediator;

import mum.asd.domain.HotelUser;
import mum.asd.domain.Promotion;
import mum.asd.patterns.FactoryMethod.ConcretePromotionFactory;
import mum.asd.patterns.FactoryMethod.HolidayPromotion;
import mum.asd.patterns.Singleton.PromotionRepositorySingleton;
import mum.asd.repository.HotelUserRepository;
import mum.asd.repository.PromotionRepository;
import mum.asd.service.ApplicationContextHolder;
import mum.asd.service.CardService;
import mum.asd.service.HotelUserService;
import mum.asd.service.impl.PromotionService;

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
//        PromotionRepository promotionRepository = ApplicationContextHolder.getContext().getBean(PromotionRepository.class);
        PromotionRepository promotionRepository = PromotionRepositorySingleton.getInstance().getPromotionRepository();
        promotionRepository.save(promotion);
        HotelUserRepository hotelUserRepository = ApplicationContextHolder.getContext().getBean(HotelUserRepository.class);
        hotelUserRepository.save(hotelUser);
    }
}
