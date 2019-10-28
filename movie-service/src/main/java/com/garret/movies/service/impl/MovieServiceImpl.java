package com.garret.movies.service.impl;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.dao.repository.MovieRepository;
import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.dto.MovieDto;
import com.google.inject.internal.util.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public MovieDto save(@NonNull MovieDto movieDto) {
        return movieRepository.findByImdbId(movieDto.getImdbId()).map(this::convertToDto).orElseGet(() -> {
            log.info("Saving movie " + movieDto.getTitle());
            Movie movie = convertToEntity(movieDto);
            Movie savedMovie = movieRepository.save(movie);
            return convertToDto(savedMovie);
        });
    }

    @Override
    @Transactional
    public List<MovieDto> saveAll(@NonNull List<MovieDto> movieDtoList) {
        List<Movie> movieList = movieDtoList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        log.info("Saving " + movieList.size() + " movies to database");
        List<Movie> savedMovies = new ArrayList<>();
        movieRepository.saveAll(movieList).forEach(savedMovies::add);
        return savedMovies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovieDto> getById(@NonNull Long id) {
        return movieRepository.findById(id).map(this::convertToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getAll() {
        Iterable<Movie> iterable = movieRepository.findAll();
        List<Movie> result = ImmutableList.copyOf(iterable);
        Type listType = new TypeToken<List<MovieDto>>() {
        }.getType();
        return modelMapper.map(result, listType);
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

    private Movie convertToEntity(MovieDto movieDTO) {
        return modelMapper.map(movieDTO, Movie.class);
    }
}
