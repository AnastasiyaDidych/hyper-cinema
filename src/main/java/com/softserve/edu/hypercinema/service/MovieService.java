
package com.softserve.edu.hypercinema.service;
import com.softserve.edu.hypercinema.entity.MovieEntity;

import java.util.List;

public interface MovieService {
    List<MovieEntity> getAllMovies();
    MovieEntity createMovie(MovieEntity movieEntity);
    MovieEntity getMovieById(Long id);
//    MovieEntity getMovieByTitle(String title);
    void updateMovie(MovieEntity movie);
    void deleteById(Long id);
}
