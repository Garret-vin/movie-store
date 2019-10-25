package com.garret.movies.service.impl;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.dao.repository.MovieRepository;
import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.dto.MovieDto;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
        Optional<Movie> movieFromDb = movieRepository.findById(id);
        MovieDto movieDTO = modelMapper.map(movieFromDb, MovieDto.class);//TODO java 8 style
        return Optional.of(movieDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getAll() {
        List<Movie> result = new ArrayList<>();
        movieRepository.findAll().forEach(result::add);
        Type listType = new TypeToken<List<MovieDto>>() {}.getType();
        return modelMapper.map(result, listType);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovieDto> getByImdbId(@NonNull String imdbId) {
        return movieRepository.findByImdbId(imdbId).map(this::convertToDto);
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
    public boolean existsByImdbId(@NonNull String imdbId) {
        return movieRepository.existsByImdbId(imdbId);
    }

    @Override
    @Transactional
    public void deleteAll() {
        movieRepository.deleteAll();
    }

    public MovieDto convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDto.class);
    }

    public Movie convertToEntity(MovieDto movieDTO) {
        return modelMapper.map(movieDTO, Movie.class);
    }
}
