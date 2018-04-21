package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.CoefficientEntity;
import com.softserve.edu.hypercinema.exception.CoefficientNotFoundException;
import com.softserve.edu.hypercinema.repository.CoefficientRepository;
import com.softserve.edu.hypercinema.service.CoefficientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CoefficientServiceImpl implements CoefficientService {

    private static final String COEFFICIENT_NOT_FOUND_EXCEPTION = "Could not find coefficient with id= ";

    @Autowired
    private CoefficientRepository coefficientRepository;

    @Override
    public CoefficientEntity createCoefficient(CoefficientEntity coefficientEntity) {
        return coefficientRepository.save(coefficientEntity);
    }

    @Override
    public CoefficientEntity getCoefficient(Long id) {
        return coefficientRepository.findById(id).orElseThrow(() -> new CoefficientNotFoundException(COEFFICIENT_NOT_FOUND_EXCEPTION));
    }

    @Override
    public List<CoefficientEntity> getCoefficients() {
        return coefficientRepository.findAll();
    }

    @Override
    public void updateCoefficient(CoefficientEntity coefficientEntity) {
        coefficientRepository.save(coefficientEntity);
    }

    @Override
    public void deleteCoefficient(Long id) {
        coefficientRepository.deleteById(id);
    }
}
