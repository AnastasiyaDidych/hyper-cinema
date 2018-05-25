package com.softserve.edu.hypercinema.dto;


import lombok.Data;

import java.util.List;

@Data
public class HallDto extends BaseDto {

    private Long id;

    private String name;

    private int capacity;

    private String type;

    private String tech;

    private List<SeatDto> seats;
}
