package com.garret.movies.dao.repository;

import com.garret.movies.dao.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByImdbId(String imdbId);

    boolean existsByImdbId(String imdbId);
}
