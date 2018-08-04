package mum.asd.patterns.FactoryMethod;

import mum.asd.domain.Promotion;

public interface PromotionFactory {
    public HolidayPromotion createPromotion(String promotionName);
}
