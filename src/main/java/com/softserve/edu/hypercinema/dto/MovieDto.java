package com.softserve.ua.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieDto {
    private long id;
    private String title;
    private String description;
    private String genre;

    public MovieDto(long id, String title, String description, String genre) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
