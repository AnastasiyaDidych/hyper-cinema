package com.softserve.edu.hypercinema.dto;

import lombok.Data;

@Data
public class SessionDto extends BaseDto {

    private Long movieId;

    private Long hallId;

    private String date;

    private String startTime;

}
