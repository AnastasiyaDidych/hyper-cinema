package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.PriceEntity;

import java.util.List;

public interface PriceService {

    PriceEntity addPrice(PriceEntity priceEntity);
    PriceEntity findPriceById(Long id);
    List<PriceEntity> listOfPrices();
    void updatePrice(PriceEntity priceEntity);
    void removePrice(PriceEntity priceEntity);
}
