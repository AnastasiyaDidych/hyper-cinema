package com.softserve.edu.hypercinema.dto;


import lombok.Data;

@Data
public class HallDto extends BaseDto {

    private String name;

    private int capacity;

    private String type;

    private String tech;
}
