
package com.softserve.edu.hypercinema.controller;


import com.softserve.edu.hypercinema.converter.MovieConverter;
import com.softserve.edu.hypercinema.dto.MovieDto;
import com.softserve.edu.hypercinema.entity.MovieEntity;

import com.softserve.edu.hypercinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<MovieEntity> getAllMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable("id") Long id ){
        return movieConverter.convertToDto(movieService.getMovie(id));
    }

    @PostMapping
    public void createMovie(@RequestBody MovieDto movieDto) {
        movieService.createMovie(movieConverter.convertToEntity(movieDto));
    }

    @PutMapping
    public void updateMovie(@RequestBody MovieDto movieDto) {
        movieService.updateMovie(movieConverter.convertToEntity(movieDto));
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
    }


}
