package com.garret.movies.dao.repository;

import com.garret.movies.dao.entity.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Optional<Movie> findByImdbId(String imdbId);

    boolean existsByImdbId(String imdbId);

    int removeById(Long id);
}
