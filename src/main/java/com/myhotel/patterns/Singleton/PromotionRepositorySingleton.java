package com.myhotel.patterns.Singleton;

import com.myhotel.domain.Promotion;
import com.myhotel.repository.PromotionRepository;
import com.myhotel.service.ApplicationContextHolder;

public enum PromotionRepositorySingleton {
	INSTANCE;

	public PromotionRepository getPromotionRepository() {
		if (promotionRepository == null) {
			promotionRepository = ApplicationContextHolder.getContext().getBean(PromotionRepository.class);
		}

		return promotionRepository;
	}

	private PromotionRepository promotionRepository;
}
