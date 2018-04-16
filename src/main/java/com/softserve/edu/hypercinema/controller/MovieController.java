package com.softserve.ua.controller;

import com.softserve.ua.entity.MovieEntity;
import com.softserve.ua.repository.MovieRepository;
import com.softserve.ua.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;


    @GetMapping("/movies/random")
    public List<MovieEntity> getRandom() {
        for(int i =1;i<=5;i++) {
            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setTitle("title " + i);
            movieEntity.setDescription("description " + i);
            movieEntity.setStartRent(LocalDate.of(i,i,i));
            movieEntity.setEndRent(LocalDate.of(i,i,i));
            //MovieRentEntity movieRent = new MovieRentEntity(LocalDate.of(i,i,i),LocalDate.of(i,i,i));
            //movie.setMovieRent(movieRent);
            movieRepository.save(movieEntity);

        }
        return movieService.getAllMovies();
    }

    @GetMapping("/movies")
    public List<MovieEntity> getAllMovies() {

        return movieService.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public List<MovieEntity> getMovieById(@PathVariable("id") Long id) {

         movieService.deleteById(id);
         return movieService.getAllMovies();
    }


}
