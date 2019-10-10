package com.garret.movies.service;

import com.garret.movies.dao.entity.Movie;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovieService {

    Movie save(Movie movie);

    List<Movie> saveAll(List<Movie> movies);

    Optional<Movie> getById(Long id);

    List<Movie> getAll();

    Optional<Movie> getByImdbId(String imdbId);

    List<Movie> getAllByGenre(String genre);

    List<Movie> getAllByActorsLastName(String lastName);

    List<Movie> getAllByYear(Date start, Date end);

    void deleteById(Long id);

    boolean isNotPresent(Movie movie);
}
