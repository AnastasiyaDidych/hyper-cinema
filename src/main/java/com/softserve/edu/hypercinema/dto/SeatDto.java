package com.softserve.edu.hypercinema.dto;

import com.softserve.edu.hypercinema.entity.HallEntity;
import lombok.Data;

@Data
public class SeatDto extends BaseDto{

    private Long id;

    private int row;

    private int number;

    private String type;

    private Long hall_id;
}
