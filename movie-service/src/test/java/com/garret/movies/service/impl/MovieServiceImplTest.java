package com.garret.movies.service.impl;

import com.garret.movies.dao.entity.*;
import com.garret.movies.dao.repository.MovieRepository;
import com.garret.movies.service.dto.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private MovieServiceImpl movieService;
    private static Movie movie;
    private static MovieDto movieDto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setId(2L);
        movie.setImdbId("tt223344");
        Country country = new Country();
        country.setName("USA");
        movie.setCountries(Collections.singletonList(country));
        Genre genre = new Genre();
        genre.setValue("Action");
        movie.setGenres(Collections.singletonList(genre));
        Actor actor = new Actor();
        actor.setFullName("Nikole Kidman");
        movie.setActors(Collections.singletonList(actor));
        Language language = new Language();
        language.setValue("English");
        movie.setLanguages(Collections.singletonList(language));

        movieDto = new MovieDto();
        movieDto.setImdbId("tt223344");
        CountryDto countryDto = new CountryDto();
        country.setName("USA");
        movieDto.setCountries(Collections.singletonList(countryDto));
        GenreDto genreDto = new GenreDto();
        genre.setValue("Action");
        movieDto.setGenres(Collections.singletonList(genreDto));
        ActorDto actorDto = new ActorDto();
        actor.setFullName("Nikole Kidman");
        movieDto.setActors(Collections.singletonList(actorDto));
        LanguageDto languageDto = new LanguageDto();
        language.setValue("English");
        movieDto.setLanguages(Collections.singletonList(languageDto));
    }

    @Test
    public void save() {
        when(movieRepository.findByImdbId(anyString())).thenReturn(Optional.empty());
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        when(modelMapper.map(movieDto, Movie.class)).thenReturn(movie);
        when(modelMapper.map(movie, MovieDto.class)).thenReturn(movieDto);
        MovieDto result = movieService.save(movieDto);
        assertThat(result)
                .isNotNull()
                .isEqualToComparingOnlyGivenFields(movie);
        assertThat(result.getImdbId()).isEqualTo("tt223344");
        verify(movieRepository).save(any(Movie.class));
        verify(movieRepository).findByImdbId(any());
        verify(modelMapper).map(movieDto, Movie.class);
        verify(modelMapper).map(movie, MovieDto.class);
    }

    @Test
    public void saveExistedMovie() {
        when(movieRepository.findByImdbId(anyString())).thenReturn(Optional.of(movie));
        when(modelMapper.map(movie, MovieDto.class)).thenReturn(movieDto);
        MovieDto result = movieService.save(movieDto);

        assertThat(result)
                .isNotNull()
                .isEqualToComparingOnlyGivenFields(movie);
        assertThat(result.getImdbId()).isEqualTo("tt223344");
        verify(movieRepository, never()).save(any());
        verify(movieRepository).findByImdbId(movie.getImdbId());
        verify(modelMapper).map(movie, MovieDto.class);
        verify(modelMapper, never()).map(movieDto, Movie.class);
    }

    @Test
    public void saveAll() {
        Type movieListType = new TypeToken<List<Movie>>() {
        }.getType();
        Type movieDtoListType = new TypeToken<List<MovieDto>>() {
        }.getType();
        List<Movie> movieList = Collections.singletonList(movie);
        List<MovieDto> dtoList = Collections.singletonList(movieDto);
        when(movieRepository.saveAll(anyIterable())).thenReturn(movieList);
        when(modelMapper.map(dtoList, movieListType)).thenReturn(movieList);
        when(modelMapper.map(movieList, movieDtoListType)).thenReturn(dtoList);
        List<MovieDto> resultList = movieService.saveAll(Collections.singletonList(movieDto));

        assertThat(resultList)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(movieDto);
        verify(movieRepository).saveAll(anyIterable());
        verify(modelMapper).map(dtoList, movieListType);
        verify(modelMapper).map(movieList, movieDtoListType);
    }

    @Test
    public void getById() {
        when(movieRepository.findById(2L)).thenReturn(Optional.of(movie));
        when(modelMapper.map(movie, MovieDto.class)).thenReturn(movieDto);
        Optional<MovieDto> result = movieService.getById(2L);

        assertThat(result)
                .isNotEmpty()
                .contains(movieDto);
        verify(movieRepository).findById(2L);
        verify(modelMapper).map(movie, MovieDto.class);
    }

    @Test
    public void getByIdWhenEmptyResultReturn() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<MovieDto> result = movieService.getById(345L);

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository).findById(345L);
        verify(modelMapper, never()).map(movie, MovieDto.class);
    }

    @Test
    public void getAll() {
        List<Movie> movieList = Collections.singletonList(movie);
        when(movieRepository.findAll()).thenReturn(movieList);
        Type listType = new TypeToken<List<MovieDto>>() {
        }.getType();
        List<MovieDto> dtoList = Collections.singletonList(movieDto);
        when(modelMapper.map(movieList, listType)).thenReturn(dtoList);

        List<MovieDto> result = movieService.getAll();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(movieDto);
        verify(movieRepository).findAll();
        verify(modelMapper).map(movieList, listType);
    }

    @Test
    public void getByImdbId() {
        when(movieRepository.findByImdbId(movie.getImdbId())).thenReturn(Optional.of(movie));
        when(modelMapper.map(movie, MovieDto.class)).thenReturn(movieDto);
        Optional<MovieDto> result = movieService.getByImdbId(movie.getImdbId());

        assertThat(result)
                .isNotEmpty()
                .contains(movieDto);
        verify(movieRepository).findByImdbId(movie.getImdbId());
        verify(modelMapper).map(movie, MovieDto.class);
    }

    @Test
    public void getByImdbIdWhenEmptyResultReturn() {
        when(movieRepository.findByImdbId("tf234566")).thenReturn(Optional.empty());
        Optional<MovieDto> result = movieService.getByImdbId("tf234566");

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository).findByImdbId("tf234566");
    }

    @Test
    public void existsInDb() {
        when(movieRepository.existsByImdbId(movie.getImdbId())).thenReturn(true);
        boolean result = movieService.existsByImdbId(movie.getImdbId());

        assertThat(result).isTrue();
        verify(movieRepository).existsByImdbId(movie.getImdbId());
    }

    @Test
    public void existsInDbFailTest() {
        when(movieRepository.existsByImdbId(movie.getImdbId())).thenReturn(false);
        boolean result = movieService.existsByImdbId(movie.getImdbId());

        assertThat(result).isFalse();
        verify(movieRepository).existsByImdbId(movie.getImdbId());
    }

    @Test
    public void deleteById() {
        Long id = 2L;
        doNothing().when(movieRepository).deleteById(id);
        movieService.deleteById(id);

        verify(movieRepository).deleteById(id);
    }

    @Test
    public void deleteAll() {
        doNothing().when(movieRepository).deleteAll();
        movieService.deleteAll();

        verify(movieRepository).deleteAll();
    }
}
