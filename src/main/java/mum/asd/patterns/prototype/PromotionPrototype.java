package mum.asd.patterns.prototype;

import mum.asd.domain.Promotion;
import org.springframework.beans.factory.annotation.Required;

public interface PromotionPrototype {
    @Required
    public Promotion doClone();
}
