//package com.softserve.ua.serviceImpl;
//
//import com.softserve.ua.entity.MovieRentEntity;
//import com.softserve.ua.repository.MovieRentRepository;
//import com.softserve.ua.service.MovieRentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class MovieRentServiceImpl implements MovieRentService {
//
//    @Autowired
//    private MovieRentRepository movieRentRepository;
//
//    @Override
//    public List<MovieRentEntity> findAll() {
//        return movieRentRepository.findAll();
//    }
//
//    @Override
//    public MovieRentEntity findById(Long id) {
//        return movieRentRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public MovieRentEntity updateMovieRent(MovieRentEntity movieRent) {
//        return movieRentRepository.save(movieRent);
//    }
//
//    @Override
//    public void deleteMovieRentById(Long id) {
//        movieRentRepository.deleteById(id);
//    }
//}
