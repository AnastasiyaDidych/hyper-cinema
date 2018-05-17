package com.softserve.edu.hypercinema.dto;

import com.softserve.edu.hypercinema.entity.HallEntity;
import com.softserve.edu.hypercinema.entity.MovieEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class SessionDto extends BaseDto {


    private Long movieId;
    private Long hallId;
    private String date;
    private String startTime;
    private boolean virtualActive;
    private BigDecimal basePrice;
    private BigDecimal vipPrice;
    private List<TicketDto> tickets;

}
