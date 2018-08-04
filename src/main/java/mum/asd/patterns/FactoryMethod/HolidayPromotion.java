package mum.asd.patterns.FactoryMethod;

import mum.asd.domain.Promotion;
import mum.asd.patterns.Command.PromotionCommandInterface;
import mum.asd.patterns.prototype.PromotionPromotionPrototypeImpl;
import mum.asd.patterns.prototype.PromotionPrototype;

public abstract class HolidayPromotion extends Promotion implements PromotionCommandInterface {
    public Promotion getPromotion(){
        PromotionPrototype promotionPrototype = new PromotionPromotionPrototypeImpl(this.getName(),this.getDiscount(),this.getPercent());
        Promotion promotion = promotionPrototype.doClone();
        return promotion;
    }

    public abstract int executeGetDiscout();
}
