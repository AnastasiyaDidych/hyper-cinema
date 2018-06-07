package com.softserve.edu.hypercinema.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto extends BaseDto {

    private Long id;
    private boolean pended;
    private boolean confirmed;
    private BigDecimal orderTotal;
    private LocalDateTime orderDate;
    private String orderNumber;
//    private PaymentDto paymentDto;

//    private List<TicketDto> tickets;
    private List<TicketFullDto> tickets;


}
