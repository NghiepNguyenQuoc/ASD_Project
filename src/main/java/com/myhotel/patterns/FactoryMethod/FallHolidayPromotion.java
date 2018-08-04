package com.myhotel.patterns.FactoryMethod;

import com.myhotel.patterns.Command.PromotionCommandInterface;

public class FallHolidayPromotion extends HolidayPromotion implements PromotionCommandInterface{
    public FallHolidayPromotion(){
//        super("FallHoliday",10,25);
        this.setDiscount(10);
        this.setPercent(25);
        this.setName("FallHoliday");
    }

    @Override
    public int executeGetDiscout() {
        return new ComputeDiscount().getDiscount(this);
//        return this.getDiscount()>this.getPercent()?this.getDiscount():(int) this.getPercent();
    }

}
