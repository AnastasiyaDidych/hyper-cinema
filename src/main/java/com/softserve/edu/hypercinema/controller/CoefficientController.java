package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.CoefficientConverter;
import com.softserve.edu.hypercinema.dto.CoefficientDto;
import com.softserve.edu.hypercinema.service.CoefficientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/coefficients")
public class CoefficientController {

    @Autowired
    private CoefficientService coefficientService;

    @Autowired
    private CoefficientConverter coefficientConverter;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCoefficient(@RequestBody CoefficientDto coefficientDto) {
        coefficientService.createCoefficient(coefficientConverter.convertToEntity(coefficientDto));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}")
    public void updateCoefficient(@RequestBody CoefficientDto coefficientDto) {
        coefficientService.updateCoefficient(coefficientConverter.convertToEntity(coefficientDto));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{id}")
    public CoefficientDto getCoefficient(@PathVariable Long id) {
        return coefficientConverter.convertToDto(coefficientService.getCoefficient(id));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public List<CoefficientDto> getCoefficients(){
        return coefficientConverter.convertToDto(coefficientService.getCoefficients());
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteCoefficient(@PathVariable Long id) {
        coefficientService.deleteCoefficient(id);
    }

}
