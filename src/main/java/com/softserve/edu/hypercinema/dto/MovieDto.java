
package com.softserve.edu.hypercinema.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

@Data
public class MovieDto extends BaseDto{

    private long id;
    private String title;

    private String description;

    private int duration;

    private String genre;

    private double tmdbId;

    private LocalDate startRent;

    private LocalDate endRent;

    private int ageRating;

    private BigDecimal price;

}
