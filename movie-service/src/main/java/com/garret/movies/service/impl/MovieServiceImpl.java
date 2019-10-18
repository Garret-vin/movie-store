package com.garret.movies.service.impl;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.dao.repository.MovieRepository;
import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.exception.IncorrectDateException;
import com.google.inject.internal.util.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Override
    @Transactional
    public Movie save(@NonNull Movie movie) {
        Optional<Movie> optionalMovie = movieRepository.findByImdbId(movie.getImdbId());
        if (optionalMovie.isPresent()) {
            log.info(movie.getTitle() + " already present");
            return optionalMovie.get();
        }
        log.info("Start saving movie " + movie.getTitle());
        Movie savedMovie = movieRepository.save(movie);
        log.info(movie.getTitle() + " was saved to database");
        return savedMovie;
    }

    @Override
    @Transactional
    public List<Movie> saveAll(@NonNull List<Movie> movies) {
        movieRepository.saveAll(movies);
        log.info(movies.size() + " movies saved to database");
        return movies;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> getById(@NonNull Long id) {
        return movieRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAll() {
        return ImmutableList.copyOf(movieRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> getByImdbId(@NonNull String imdbId) {
        return movieRepository.findByImdbId(imdbId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllByGenre(@NonNull String genre) {
        return movieRepository.findAllByGenresValue(genre);
    }

    @Override
    public List<Movie> getAllByActor(@NonNull String actor) {
        return movieRepository.findAllByActorsFullNameContains(actor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllByYear(@NonNull Integer year) {
        String start = year + "-01-01";
        String end = year + "-12-31";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = formatter.parse(start);
            Date endDate = formatter.parse(end);
            return movieRepository.findAllByReleasedBetween(startDate, endDate);
        } catch (ParseException e) {
            log.error("Incorrect date in MovieServiceImpl.class throws from method: getAllByYear()", e);
            throw new IncorrectDateException("Can't parse date from DB");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllByLanguage(@NonNull String language) {
        return movieRepository.findAllByLanguagesValue(language);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllByCountry(@NonNull String country) {
        return movieRepository.findAllByCountry(country);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getTopByVotes() {
        return movieRepository.findAllByOrderByImdbVotesDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getTopByRating() {
        return movieRepository.findAllByOrderByImdbRatingDesc();
    }

    @Override
    @Transactional
    public boolean deleteById(@NonNull Long id) {
        boolean isDeleted = false;
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            isDeleted = true;
        }
        log.info("Deleted movie with id = " + id + " is " + isDeleted);
        return isDeleted;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsInDb(@NonNull Movie movie) {
        return movieRepository.existsByImdbId(movie.getImdbId());
    }
}
