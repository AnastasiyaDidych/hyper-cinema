package com.softserve.edu.hypercinema.dto;

import com.softserve.edu.hypercinema.entity.OrderEntity;
import com.softserve.edu.hypercinema.entity.PriceEntity;
import com.softserve.edu.hypercinema.entity.SeatEntity;
import com.softserve.edu.hypercinema.entity.SessionEntity;
import lombok.Data;

@Data
public class TicketDto extends BaseDto{

    private OrderEntity orderEntity;

    private SessionEntity sessionEntity;

    private PriceEntity priceEntity;

    private SeatEntity seatEntity;

}
