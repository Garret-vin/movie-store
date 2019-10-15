package com.garret.movies.service.impl;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.dao.repository.MovieRepository;
import com.garret.movies.service.api.MovieService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Movie> saveAll(@NonNull List<Movie> movies) {
        movieRepository.saveAll(movies);
        log.info(movies.size() + " movies saved to database");
        return movies;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> getById(@NonNull Long id) {
        log.info("Getting movie from database by id = " + id);
        return movieRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAll() {
        log.info("Getting all movies from database");
        return (List<Movie>) movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getByImdbId(String imdbId) {
        return movieRepository.findByImdbId(imdbId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllByGenre(@NonNull String genre) {
        return movieRepository.findAllByGenresValue(genre);
    }

    @Override
    public List<Movie> getAllByActorsLastName(@NonNull String lastName) {
        return movieRepository.findAllByActorsLastName(lastName);
    }

    @Override
    public List<Movie> getAllByYear(Date start, Date end) {
        return movieRepository.findAllByReleasedBetween(start, end);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
        log.info("Deleted movie with id = " + id);
    }

    @Override
    public boolean existsInDb(Movie movie) {
        Optional<Movie> opt = getByImdbId(movie.getImdbId());
        return opt.isPresent();
    }
}
