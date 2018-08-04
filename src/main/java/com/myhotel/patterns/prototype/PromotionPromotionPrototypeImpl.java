package com.myhotel.patterns.prototype;

import com.myhotel.domain.Promotion;

public class PromotionPromotionPrototypeImpl extends Promotion implements PromotionPrototype {
    public PromotionPromotionPrototypeImpl(String name, int discount, float percent) {
        super(name, discount, percent);
    }

    public PromotionPromotionPrototypeImpl(Promotion promotion){
        super(promotion.getName(),promotion.getDiscount(),promotion.getPercent());
    }

    @Override
    public Promotion doClone(){
        return new Promotion(this.getName(),this.getDiscount(), this.getPercent());
    }
}
