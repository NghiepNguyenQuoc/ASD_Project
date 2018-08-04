package com.myhotel.patterns.FactoryMethod;

import com.myhotel.patterns.Command.PromotionCommandInterface;

public class SpringHolidayPromotion extends HolidayPromotion implements PromotionCommandInterface {
    PromotionName promotionName;
    public SpringHolidayPromotion(){
//        super("SpringHoliday",15,20);
        this.setDiscount(15);
        this.setPercent(20);
        this.setName("SpringHoliday");
        this.promotionName = PromotionName.SpringHoliday;
    }
    @Override
    public int executeGetDiscout() {
        return new ComputeDiscount().getDiscount(this);
//        return this.getDiscount()+(int)this.getPercent();
    }

}
