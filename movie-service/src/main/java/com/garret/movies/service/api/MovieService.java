package com.garret.movies.service.api;

import com.garret.movies.dao.entity.Movie;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovieService {

    Movie save(Movie movie);

    List<Movie> saveAll(List<Movie> movies);

    Optional<Movie> getById(Long id);

    List<Movie> getAllByParams(Map<String, String> params);

    boolean deleteById(Long id);

    boolean existsByImdbId(String imdbId);

    void deleteAll();
}
