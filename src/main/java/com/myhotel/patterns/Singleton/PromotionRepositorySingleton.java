package com.myhotel.patterns.Singleton;

import com.myhotel.domain.Promotion;
import com.myhotel.repository.PromotionRepository;
import com.myhotel.service.ApplicationContextHolder;

public class PromotionRepositorySingleton {
    public PromotionRepository getPromotionRepository() {
        return promotionRepository;
    }

    private PromotionRepository promotionRepository;
    private static PromotionRepositorySingleton Instance = null;

    private PromotionRepositorySingleton(){}

    public static PromotionRepositorySingleton getInstance(){
        if (Instance == null){
            Instance = new PromotionRepositorySingleton();
            Instance.promotionRepository = ApplicationContextHolder.getContext().getBean(PromotionRepository.class);
        }
        return Instance;
    }
}
