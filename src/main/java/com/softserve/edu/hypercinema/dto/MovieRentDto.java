package com.softserve.ua.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieRentDto {

    private long id;
    private String startRent;
    private String endRent;

    public MovieRentDto(long id, String startRent, String endRent) {
        this.id = id;
        this.startRent = startRent;
        this.endRent = endRent;
    }

    @Override
    public String toString() {
        return "MovieRentDto{" +
                "id=" + id +
                ", startRent='" + startRent + '\'' +
                ", endRent='" + endRent + '\'' +
                '}';
    }
}
