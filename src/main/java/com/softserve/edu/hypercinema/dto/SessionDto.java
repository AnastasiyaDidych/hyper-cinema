package com.softserve.edu.hypercinema.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SessionDto extends BaseDto {

    private Long id;
    private Long movieId;
    private Long hallId;
    private String date;
    private String startTime;
    private String endTime;
    private boolean virtualActive;
    private BigDecimal basePrice;
    private BigDecimal vipPrice;
    private String title;

}
