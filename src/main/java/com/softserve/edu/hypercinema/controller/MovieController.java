package com.softserve.edu.hypercinema.controller;


import com.softserve.edu.hypercinema.converter.MovieConverter;
import com.softserve.edu.hypercinema.dto.MovieDto;
import com.softserve.edu.hypercinema.entity.MovieEntity;

import com.softserve.edu.hypercinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    @GetMapping("/random")
    public List<MovieDto> getRandom() {
        for(int i =1;i<=5;i++) {
            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setTitle("title " + i);
            movieEntity.setDescription("description " + i);
            movieEntity.setStartRent(LocalDate.of(i,i,i));
            movieEntity.setEndRent(LocalDate.of(i,i,i));

            movieService.createMovie(movieEntity);

        }
        return movieConverter.convertToDto(movieService.getMovies());
    }

    @GetMapping
    //@PreAuthorize("hasRole('USER')")
    public List<MovieDto> getAllMovies() {
        return movieConverter.convertToDto(movieService.getMovies());
    }

    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable("id") Long id ){
        return movieConverter.convertToDto(movieService.getMovie(id));
    }

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMovie(@RequestBody MovieDto movieDto) {
        movieService.createMovie(movieConverter.convertToEntity(movieDto));
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void updateMovie(@RequestBody MovieDto movieDto, @PathVariable Long id) {
        movieService.updateMovie(movieConverter.convertToEntity(movieDto),id);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
    }

}
