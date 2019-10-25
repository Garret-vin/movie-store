package com.garret.movies.service.api;

import com.garret.movies.service.dto.MovieDto;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    MovieDto save(MovieDto movie);

    List<MovieDto> saveAll(List<MovieDto> movies);

    Optional<MovieDto> getById(Long id);

    List<MovieDto> getAll();

    Optional<MovieDto> getByImdbId(String imdbId);

    boolean deleteById(Long id);

    boolean existsByImdbId(String imdbId);

    void deleteAll();
}
