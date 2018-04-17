package com.softserve.edu.hypercinema.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

@Data
public class MovieDto extends BaseDto{

    private String title;

    private String description;

    private Duration duration;

    private String genre;

    private LocalDate startRent;

    private int ageRating;

    private BigDecimal price;

}
