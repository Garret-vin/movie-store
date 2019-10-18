package com.garret.movies.dao.repository;

import com.garret.movies.dao.entity.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Optional<Movie> findByImdbId(String imdbId);

    List<Movie> findAllByGenresValue(String genre);

    List<Movie> findAllByActorsFullNameContains(String actor);

    List<Movie> findAllByReleasedBetween(Date start, Date end);

    List<Movie> findAllByLanguagesValue(String language);

    List<Movie> findAllByOrderByImdbVotesDesc();

    List<Movie> findAllByOrderByImdbRatingDesc();

    List<Movie> findAllByCountry(String country);

    boolean existsByImdbId(String imdbId);
}
