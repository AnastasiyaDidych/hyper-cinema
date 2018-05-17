package com.softserve.edu.hypercinema.dto;

import lombok.Data;

@Data
public class SeatDto extends BaseDto{

    private Long id;

    private int row;

    private int number;

    private String type;

}
