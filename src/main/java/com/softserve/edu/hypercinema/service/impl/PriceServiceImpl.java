package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.PriceEntity;
import com.softserve.edu.hypercinema.exception.PriceNotFoundException;
import com.softserve.edu.hypercinema.repository.PriceRepository;
import com.softserve.edu.hypercinema.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PriceServiceImpl implements PriceService {

    public static final String PRICE_NOT_FOUND_MESSAGE = "Could not find price with id= ";

    @Autowired
    PriceRepository priceRepository;

    @Override
    public PriceEntity addPrice(PriceEntity priceEntity) {
        return priceRepository.save(priceEntity);
    }

    @Override
    public PriceEntity findPriceById(Long id) {
        return priceRepository.findById(id).orElseThrow(() -> new PriceNotFoundException(PRICE_NOT_FOUND_MESSAGE));
    }

    @Override
    public List<PriceEntity> listOfPrices() {
        return priceRepository.findAll();
    }

    @Override
    public void updatePrice(PriceEntity priceEntity) {
        priceRepository.save(priceEntity);
    }

    @Override
    public void removePrice(PriceEntity priceEntity) {
        priceRepository.delete(priceEntity);
    }
}
