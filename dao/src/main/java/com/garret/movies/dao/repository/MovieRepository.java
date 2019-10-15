package com.garret.movies.dao.repository;

import com.garret.movies.dao.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    Optional<Movie> findByImdbId(String imdbId);

    List<Movie> findAllByGenresValue(String genre);

    List<Movie> findAllByActorsLastName(String lastName);

    List<Movie> findAllByReleasedBetween(Date start, Date end);

    List<Movie> findAllByLanguagesValue(String language);
}
