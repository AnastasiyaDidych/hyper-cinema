package com.softserve.edu.hypercinema.controller;


import com.softserve.edu.hypercinema.converter.MovieConverter;
import com.softserve.edu.hypercinema.dto.MovieDto;
import com.softserve.edu.hypercinema.entity.MovieEntity;

import com.softserve.edu.hypercinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<MovieDto> getAllMovies() {
        return movieConverter.convertToDto(movieService.getMovies());

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public MovieDto getMovieById(@PathVariable("id") Long id ){
        return movieConverter.convertToDto(movieService.getMovie(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMovie(@RequestBody MovieDto movieDto) {
        movieService.createMovie(movieConverter.convertToEntity(movieDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public void updateMovie(@RequestBody MovieDto movieDto, @PathVariable("id") Long id) {
        MovieEntity movieEntity = movieConverter.convertToEntity(movieDto);
        movieService.updateMovie(movieEntity,id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public void deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
    }

}
