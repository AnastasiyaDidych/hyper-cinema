package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.converter.MovieConverter;
import com.softserve.edu.hypercinema.dto.MovieDto;
import com.softserve.edu.hypercinema.entity.MovieEntity;
import com.softserve.edu.hypercinema.exception.InvalidDataException;
import com.softserve.edu.hypercinema.exception.MovieAlreadyExistsException;
import com.softserve.edu.hypercinema.exception.MovieNotFoundException;
import com.softserve.edu.hypercinema.exception.WrongDateRentException;
import com.softserve.edu.hypercinema.repository.MovieRepository;
import com.softserve.edu.hypercinema.service.MovieService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.UnexpectedTypeException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@Service
public class MovieServiceImpl implements MovieService {

    private final String MOVIE_ALREADY_EXISTS_MESSAGE = "Movie with title %s already exists";
    private final String MOVIE_NOT_FOUND_MESSAGE = "Movie with id = %d has not found";
    private final String WRONG_DATE_RENT_MESSAGE = "End rent should be after start rent";

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieConverter movieConverter;

    @Override
    public List<MovieEntity> getMovies() {
        return movieRepository.findAll();
    }

    @Override

    public void createMovie(MovieEntity movieEntity) {
//        movieRepository.findByTitle(movieEntity.getTitle()).ifPresent(movie -> {
//            throw new MovieAlreadyExistsException(String.format(MOVIE_ALREADY_EXISTS_MESSAGE,movie.getTitle()));
//        });
        System.out.println(dateIsInRange(movieEntity));
        if (!dateIsInRange(movieEntity)){
            throw new WrongDateRentException(WRONG_DATE_RENT_MESSAGE);
        }
        movieRepository.save(movieEntity);
    }

    @Override
    public MovieEntity getMovie(Long id) {
        return movieRepository.findById(id).orElseThrow(() ->new MovieNotFoundException(String.format(MOVIE_NOT_FOUND_MESSAGE,id)));
    }

     public MovieEntity getMovieByTitle(String title) {
         return movieRepository.findByTitle(title).orElseThrow(() ->new MovieNotFoundException(MOVIE_NOT_FOUND_MESSAGE));
    }

    @Override
    public void updateMovie(MovieEntity movie, Long id) {
        MovieEntity movieEntity = getMovie(id);
            movie.setId(movieEntity.getId());
            createMovie(movie);
            //movieRepository.save(movie);
            log.info("Movie successfully updated");
    }

    @Override
    public void deleteMovie(Long id) {
        MovieEntity movieEntity = getMovie(id);
        movieRepository.deleteById(movieEntity.getId());
        log.info("Movie : " + movieEntity.getTitle() + " successfully deleted");
    }

    public boolean dateIsInRange(MovieEntity movieEntity) {
        return Optional.ofNullable(movieEntity)
                .map(MovieEntity::getEndRent)
                .filter(rent -> rent.isAfter(movieEntity.getStartRent()))
                .isPresent();
    }

}
