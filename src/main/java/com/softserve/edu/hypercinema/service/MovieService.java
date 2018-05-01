
package com.softserve.edu.hypercinema.service;
import com.softserve.edu.hypercinema.entity.MovieEntity;

import java.util.List;

public interface MovieService {
    List<MovieEntity> getMovies();
    void createMovie(MovieEntity movieEntity);
    MovieEntity getMovie(Long id);
    void updateMovie(MovieEntity movie, Long id);
    void deleteMovie(Long id);
}
