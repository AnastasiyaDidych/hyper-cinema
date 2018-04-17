package com.softserve.edu.hypercinema.dto;


import lombok.Data;

@Data
public class HallDto extends BaseDto {

    private String name;

    private Integer capacity;

    private String type;

}
