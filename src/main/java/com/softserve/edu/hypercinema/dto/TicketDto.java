package com.softserve.edu.hypercinema.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketDto extends BaseDto{

    private Long orderId;

    private Long sessionId;

    private BigDecimal price;

    private Long seatId;

}
