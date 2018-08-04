package com.myhotel.patterns.FactoryMethod;

import com.myhotel.domain.Promotion;

public class ConcretePromotionFactory implements PromotionFactory {
    @Override
    public HolidayPromotion createPromotion(String promotionName) {
        if (promotionName == PromotionName.FallHoliday.toString()){
            return new FallHolidayPromotion();
        }else if (promotionName == PromotionName.SpringHoliday.toString()){
            return new SpringHolidayPromotion();
        }else if (promotionName == PromotionName.SummerHoliday.toString()){
            return new SummerHolidayPromotion();
        }else if (promotionName == PromotionName.WinterHoliday.toString()){
            return new WinterHolidayPromotion();
        }
        return new SummerHolidayPromotion();
    }
}
