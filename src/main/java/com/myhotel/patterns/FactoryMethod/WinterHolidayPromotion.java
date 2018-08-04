package com.myhotel.patterns.FactoryMethod;

import com.myhotel.patterns.Command.PromotionCommandInterface;

public class WinterHolidayPromotion extends HolidayPromotion implements PromotionCommandInterface {
    public WinterHolidayPromotion(){
//        super("WinterHoliday",5,5);
        this.setDiscount(5);
        this.setPercent(5);
        this.setName("WinterHoliday");
    }
    @Override
    public int executeGetDiscout() {
        return new ComputeDiscount().getDiscount(this);
    }
}
