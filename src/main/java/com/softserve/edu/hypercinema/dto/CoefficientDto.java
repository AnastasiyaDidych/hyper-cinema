package com.softserve.edu.hypercinema.dto;

import lombok.Data;

@Data
public class CoefficientDto extends BaseDto {

    private Long id;

    private String type;

    private double value;

}
