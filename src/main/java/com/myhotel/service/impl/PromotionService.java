package com.myhotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhotel.repository.PromotionRepository;

@Service
public class PromotionService {
    @Autowired
    PromotionRepository promotionRepository;
}
