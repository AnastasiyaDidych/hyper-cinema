package com.softserve.ua.service;

import com.softserve.ua.entity.MovieEntity;

import java.util.List;

public interface MovieService {
    List<MovieEntity> getAllMovies();
    MovieEntity createMovie(MovieEntity movieEntity);
    MovieEntity getMovieById(Long id);
//    MovieEntity getMovieByTitle(String title);
    void updateMovie(MovieEntity movie);
    void deleteById(Long id);
}
