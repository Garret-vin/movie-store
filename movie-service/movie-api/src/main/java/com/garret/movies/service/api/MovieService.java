package com.garret.movies.service.api;

import com.garret.movies.service.dto.criteria.MovieCriteria;
import com.garret.movies.service.dto.response.MovieDto;
import com.garret.movies.service.dto.response.SimpleMoviesResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    MovieDto save(MovieDto movie);

    List<MovieDto> saveAll(List<MovieDto> movies);

    Optional<MovieDto> getById(Long id);

    SimpleMoviesResponse getAll(Pageable pageable);

    List<MovieDto> getByRequestParams(MovieCriteria movieCriteria);

    Optional<MovieDto> getByImdbId(String imdbId);

    void deleteById(Long id);

    boolean existsByImdbId(String imdbId);

    void deleteAll();
}
