package com.garret.movies.service.impl;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.dao.repository.MovieRepository;
import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.exception.IncorrectDateException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private MovieRepository movieRepository;

    @Override
    @Transactional
    public Movie save(@NonNull Movie movie) {
        return movieRepository.findByImdbId(movie.getImdbId()).orElseGet(() -> {
            log.info("Saving movie " + movie.getTitle());
            return movieRepository.save(movie);
        });
    }

    @Override
    @Transactional
    public List<Movie> saveAll(@NonNull List<Movie> movies) {
        List<Movie> movieList = new ArrayList<>();
        log.info("Saving " + movies.size() + " movies to database");
        movieRepository.saveAll(movies).forEach(movieList::add);
        return movieList;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> getById(@NonNull Long id) {
        return movieRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAll() {
        List<Movie> result = new ArrayList<>();
        movieRepository.findAll().forEach(result::add);
        return result;
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
    public List<Movie> getAllByYear(int year) {
        String start = year + "-01-01";
        String end = year + "-12-31";
        try {
            Date startDate = formatter.parse(start);
            Date endDate = formatter.parse(end);
            return movieRepository.findAllByReleasedBetween(startDate, endDate);
        } catch (ParseException e) {
            log.error("Wrong year specified: " + year, e);
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
    public void deleteAll() {
        movieRepository.deleteAll();
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
        boolean result = movieRepository.removeById(id) > 0;
        log.info("Delete movie with id = " + id + ": " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsInDb(@NonNull Movie movie) {
        return movieRepository.existsByImdbId(movie.getImdbId());
    }
}
