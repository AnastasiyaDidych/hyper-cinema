package com.softserve.edu.hypercinema.dto;


import lombok.Data;

import java.util.List;

@Data
public class OrderDto extends BaseDto {
    private boolean pending;
    private boolean confirmed;
    private List<TicketDto> tickets;
}
