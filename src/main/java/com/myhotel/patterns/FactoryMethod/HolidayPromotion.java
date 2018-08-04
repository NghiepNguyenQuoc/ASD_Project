package com.myhotel.patterns.FactoryMethod;

import com.myhotel.domain.Promotion;
import com.myhotel.patterns.Command.PromotionCommandInterface;
import com.myhotel.patterns.prototype.PromotionPromotionPrototypeImpl;
import com.myhotel.patterns.prototype.PromotionPrototype;

public abstract class HolidayPromotion extends Promotion implements PromotionCommandInterface {
    public Promotion getPromotion(){
        PromotionPrototype promotionPrototype = new PromotionPromotionPrototypeImpl(this.getName(),this.getDiscount(),this.getPercent());
        Promotion promotion = promotionPrototype.doClone();
        return promotion;
    }

    public abstract int executeGetDiscout();
}
