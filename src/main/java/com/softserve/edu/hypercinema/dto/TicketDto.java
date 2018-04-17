package com.softserve.edu.hypercinema.dto;


public class TicketDto extends BaseDto {

import com.softserve.edu.hypercinema.entity.OrderEntity;
import com.softserve.edu.hypercinema.entity.PriceEntity;
import com.softserve.edu.hypercinema.entity.SeatEntity;
import com.softserve.edu.hypercinema.entity.SessionEntity;
import lombok.Data;

/**
 * Created by VR
 * 18:19, 16.04.2018
 */

@Data
public class TicketDto extends BaseDto{

    private OrderEntity orderEntity;

    private SessionEntity sessionEntity;

    private PriceEntity priceEntity;

    private SeatEntity seatEntity;

}
