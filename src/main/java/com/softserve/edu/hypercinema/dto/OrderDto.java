package com.softserve.edu.hypercinema.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto extends BaseDto {

    private Long id;
    private boolean pending;
    private boolean confirmed;
    private BigDecimal price;
//    private UserDto user;
    private List<TicketDto> tickets;

}
