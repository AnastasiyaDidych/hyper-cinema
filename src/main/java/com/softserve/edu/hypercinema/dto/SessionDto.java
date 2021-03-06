package com.softserve.edu.hypercinema.dto;

import lombok.Data;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Data
public class SessionDto extends BaseDto {

    private Long id;
    private Long movieId;
    private Long hallId;
    private String date;
    private String startTime;
    private String endTime;
    private Boolean virtualActive;
    private Boolean active;
    private BigDecimal basePrice;
    private BigDecimal vipPrice;
    private BigDecimal virtualPrice;
    private String title;
    private List<TicketDto> tickets;

}
