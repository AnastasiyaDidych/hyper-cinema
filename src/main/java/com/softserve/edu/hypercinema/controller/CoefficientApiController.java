package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.CoefficientConverter;
import com.softserve.edu.hypercinema.dto.CoefficientDto;
import com.softserve.edu.hypercinema.service.CoefficientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/coefficient")
public class CoefficientApiController {

    @Autowired
    CoefficientService coefficientService;

    @Autowired
    CoefficientConverter coefficientConverter;

    @PostMapping("/create")
    public void createCoefficent(@RequestBody CoefficientDto coefficientDto) {
        coefficientService.addCoefficient(coefficientConverter.convertToEntity(coefficientDto));
    }

    @PutMapping("/update")
    public void updateCoefficient(@RequestBody CoefficientDto coefficientDto) {
        coefficientService.updateCoefficient(coefficientConverter.convertToEntity(coefficientDto));
    }
    @GetMapping("/{id}")
    public CoefficientDto getCoefficient(@PathVariable Long id) {
        return coefficientConverter.convertToDto(coefficientService.findCoefficientById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCoefficient(@PathVariable Long id) {
        coefficientService.removeCoefficientById(id);
    }
}
