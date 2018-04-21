package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.MovieEntity;
import com.softserve.edu.hypercinema.exception.MovieAlreadyExistsException;
import com.softserve.edu.hypercinema.exception.MovieNotFoundException;
import com.softserve.edu.hypercinema.repository.MovieRepository;
import com.softserve.edu.hypercinema.service.MovieService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Slf4j
@Transactional
@Service
public class MovieServiceImpl implements MovieService {

    private final String MOVIE_ALREADY_EXISTS_MESSAGE = "Movie with title %s already exists";
    private final String MOVIE_NOT_FOUND_MESSAGE = "Movie %s has not found";
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<MovieEntity> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public MovieEntity createMovie(MovieEntity movieEntity) {
        movieRepository.findByTitle(movieEntity.getTitle()).ifPresent(movie -> {
            throw new MovieAlreadyExistsException(String.format(MOVIE_ALREADY_EXISTS_MESSAGE,movie.getTitle()));
        });
        return movieRepository.save(movieEntity);
    }

    @Override
    public MovieEntity getMovie(Long id) {
        return movieRepository.findById(id).orElseThrow(() ->new MovieNotFoundException(MOVIE_NOT_FOUND_MESSAGE));
    }

    @Override
    public void updateMovie(MovieEntity movie) {
        movieRepository.save(movie);
        log.info("Movie successfully updated");
    }

    @Override
    public void deleteMovie(Long id) {
        MovieEntity movieEntity = getMovie(id);
        movieRepository.deleteById(movieEntity.getId());
        log.info("Movie : " + movieEntity.getTitle() + " successfully deleted");
    }
}
