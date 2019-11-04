package com.garret.movies.service.impl;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.dao.repository.MovieRepository;
import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.dto.SimpleMovieDto;
import com.garret.movies.service.dto.response.MovieDto;
import com.garret.movies.service.dto.response.SimpleMoviesResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private static final Type MOVIE_LIST_TYPE = new TypeToken<List<Movie>>() {
    }.getType();
    private static final Type MOVIE_DTO_LIST_TYPE = new TypeToken<List<MovieDto>>() {
    }.getType();
    private static final Type SIMPLE_MOVIE_DTO_LIST_TYPE = new TypeToken<List<SimpleMovieDto>>() {
    }.getType();
    private MovieRepository movieRepository;
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public MovieDto save(@NonNull MovieDto movieDto) {
        return movieRepository.findByImdbId(movieDto.getImdbId()).map(this::convertToDto).orElseGet(() -> {
            log.info("Saving movie " + movieDto.getTitle());
            Movie movie = modelMapper.map(movieDto, Movie.class);
            Movie savedMovie = movieRepository.save(movie);
            return convertToDto(savedMovie);
        });
    }

    @Override
    @Transactional
    public List<MovieDto> saveAll(@NonNull List<MovieDto> movieDtoList) {
        List<Movie> movieList = modelMapper.map(movieDtoList, MOVIE_LIST_TYPE);
        log.info("Saving " + movieList.size() + " movies to database");
        List<Movie> savedMovies = movieRepository.saveAll(movieList);
        return modelMapper.map(savedMovies, MOVIE_DTO_LIST_TYPE);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovieDto> getById(@NonNull Long id) {
        return movieRepository.findById(id).map(this::convertToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleMoviesResponse getAll(Pageable pageable) {
        Page<Movie> fullPage = movieRepository.findAll(pageable);
        List<SimpleMovieDto> simpleMovieList = modelMapper.map(fullPage.getContent(), SIMPLE_MOVIE_DTO_LIST_TYPE);

        SimpleMoviesResponse response = new SimpleMoviesResponse();
        response.setSearchContent(simpleMovieList);
        response.setPageSize(fullPage.getSize());
        response.setTotalElements(fullPage.getTotalElements());
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovieDto> getByImdbId(@NonNull String imdbId) {
        return movieRepository.findByImdbId(imdbId).map(this::convertToDto);
    }

    @Override
    @Transactional
    public void deleteById(@NonNull Long id) {
        movieRepository.deleteById(id);
        log.info("Deleting movie with id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByImdbId(@NonNull String imdbId) {
        return movieRepository.existsByImdbId(imdbId);
    }

    @Override
    @Transactional
    public void deleteAll() {
        movieRepository.deleteAll();
    }

    private MovieDto convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDto.class);
    }
}
