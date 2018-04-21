package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.CoefficientEntity;

import java.util.List;

public interface CoefficientService {

    CoefficientEntity createCoefficient(CoefficientEntity coefficientEntity);
    CoefficientEntity getCoefficient(Long id);
    List<CoefficientEntity> getCoefficients();
    void updateCoefficient(CoefficientEntity coefficientEntity);
    void deleteCoefficient(Long id);
}
