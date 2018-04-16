package com.softserve.ua.repository;

import com.softserve.ua.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    Optional<MovieEntity> findByTitle(String title);
}
