package com.myhotel.patterns.prototype;

import org.springframework.beans.factory.annotation.Required;

import com.myhotel.domain.Promotion;

public interface PromotionPrototype {
    @Required
    public Promotion doClone();
}
