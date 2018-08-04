package mum.asd.service.impl;

import mum.asd.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
    @Autowired
    PromotionRepository promotionRepository;
}
