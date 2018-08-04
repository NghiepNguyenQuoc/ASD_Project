package mum.asd.patterns.Singleton;

import mum.asd.domain.Promotion;
import mum.asd.repository.PromotionRepository;
import mum.asd.service.ApplicationContextHolder;

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
