package com.softserve.edu.hypercinema.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto extends BaseDto {

    private Long id;
    private boolean pending;
    private boolean confirmed;
    private BigDecimal orderTotal;
//    private List<TicketDto> tickets;
    private Date orderDate;
    private PaymentDto paymentDto;

    private List<TicketFullDto> tickets;


}
