package com.garret.movies.service.api;

import com.garret.movies.dao.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Movie save(Movie movie);

    List<Movie> saveAll(List<Movie> movies);

    Optional<Movie> getById(Long id);

    List<Movie> getAll();

    Optional<Movie> getByImdbId(String imdbId);

    List<Movie> getAllByGenre(String genre);

    List<Movie> getAllByActor(String actor);

    List<Movie> getAllByYear(Integer year);

    List<Movie> getAllByLanguage(String language);

    List<Movie> getTopByVotes();

    List<Movie> getTopByRating();

    boolean deleteById(Long id);

    boolean existsInDb(Movie movie);

    List<Movie> getAllByCountry(String country);
}
