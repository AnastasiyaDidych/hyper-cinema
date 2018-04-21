
package com.softserve.edu.hypercinema.service;
import com.softserve.edu.hypercinema.entity.MovieEntity;

import java.util.List;

public interface MovieService {
    List<MovieEntity> getMovies();
    MovieEntity createMovie(MovieEntity movieEntity);
    MovieEntity getMovie(Long id);
//    MovieEntity getMovieByTitle(String title);
    void updateMovie(MovieEntity movie);
    void deleteMovie(Long id);
}
