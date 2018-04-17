package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.CoefficientEntity;
import com.softserve.edu.hypercinema.exeption.CoefficientNotFoundExeption;
import com.softserve.edu.hypercinema.exeption.ServiceExeption;
import com.softserve.edu.hypercinema.repository.CoefficientRepository;
import com.softserve.edu.hypercinema.service.CoefficientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CoefficientServiceImpl implements CoefficientService {

    private static final String COEFFICIENT_NOT_fOUND_EXEPTION = "Could not find coefficient with id= ";

    @Autowired
    CoefficientRepository coefficientRepository;

    @Override
    public CoefficientEntity addCoefficient(CoefficientEntity coefficientEntity) {
        return coefficientRepository.save(coefficientEntity);
    }

    @Override
    public CoefficientEntity findCoefficientById(Long id) {
        return coefficientRepository.findById(id).orElseThrow(() -> new CoefficientNotFoundExeption(COEFFICIENT_NOT_fOUND_EXEPTION));
    }

    @Override
    public List<CoefficientEntity> listOfCoefficients() {
        return coefficientRepository.findAll();
    }

    @Override
    public void updateCoefficient(CoefficientEntity coefficientEntity) {
        coefficientRepository.save(coefficientEntity);
    }

    @Override
    public void removeCoefficientById(Long id) {
        coefficientRepository.deleteById(id);
    }
}
