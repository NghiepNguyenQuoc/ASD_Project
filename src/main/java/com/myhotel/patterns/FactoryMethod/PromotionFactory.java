package com.myhotel.patterns.FactoryMethod;

import com.myhotel.domain.Promotion;

public interface PromotionFactory {
    public HolidayPromotion createPromotion(String promotionName);
}
