package com.myhotel.patterns.FactoryMethod;

import com.myhotel.patterns.Command.PromotionCommandInterface;

public class SummerHolidayPromotion extends HolidayPromotion implements PromotionCommandInterface {
    public SummerHolidayPromotion(){
//        super("SummerHoliday",15,10);
        this.setDiscount(15);
        this.setPercent(10);
        this.setName("SummerHoliday");
    }
    @Override
    public int executeGetDiscout() {
        return new ComputeDiscount().getDiscount(this);
//
//        return this.getDiscount()<this.getPercent()?this.getDiscount():(int) this.getPercent();
    }

}
