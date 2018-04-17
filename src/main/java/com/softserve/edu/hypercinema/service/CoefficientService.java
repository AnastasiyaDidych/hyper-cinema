package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.CoefficientEntity;

import java.util.List;

public interface CoefficientService {

    CoefficientEntity addCoefficient(CoefficientEntity coefficientEntity);
    CoefficientEntity findCoefficientById(Long id);
    List<CoefficientEntity> listOfCoefficients();
    void updateCoefficient(CoefficientEntity coefficientEntity);
    void removeCoefficientById (Long id);
}
