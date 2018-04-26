package com.softserve.edu.hypercinema.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketDto extends BaseDto{

    private SessionDto session;

    private BigDecimal price;

    private SeatDto seat;

}
