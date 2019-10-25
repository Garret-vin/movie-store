package com.garret.movies.service.impl;

import com.garret.movies.dao.entity.*;
import com.garret.movies.dao.repository.MovieRepository;
import com.garret.movies.service.dto.MovieDto;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MovieServiceImplTest {

    private ModelMapper modelMapper = new ModelMapper();
    private MovieRepository movieRepository = mock(MovieRepository.class);
    private MovieServiceImpl movieService = new MovieServiceImpl(movieRepository, modelMapper);
    private static Movie movie;

    @BeforeClass
    public static void setUp() {
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
    }

    @Test
    public void convertToDto() {
        MovieDto movieDto = movieService.convertToDto(movie);
        assertThat(movieDto).isNotNull();
    }
    /*@Test
    public void save() {
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        when(movieRepository.findByImdbId(anyString())).thenReturn(Optional.empty());TODO

        Movie result = movieService.save(new Movie());
        assertThat(result)
                .isNotNull()
                .isEqualToComparingFieldByField(movie);
        assertThat(result.getId()).isEqualTo(2L);
        verify(movieRepository).save(any(Movie.class));
        verify(movieRepository).findByImdbId(any());
    }

    @Test
    public void saveExistedMovie() {
        when(movieRepository.findByImdbId(anyString())).thenReturn(Optional.of(movie));
        Movie result = movieService.save(movie);

        assertThat(result)
                .isNotNull()
                .isEqualToComparingFieldByField(movie);
        assertThat(result.getId()).isEqualTo(2L);
        verify(movieRepository, never()).save(any());
        verify(movieRepository).findByImdbId(movie.getImdbId());
    }

    @Test
    public void saveAll() {
        when(movieRepository.saveAll(anyIterable())).thenReturn(Collections.singletonList(movie));
        List<Movie> resultList = movieService.saveAll(Collections.singletonList(movie));

        assertThat(resultList)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(movie);
        verify(movieRepository).saveAll(anyIterable());
    }

    @Test
    public void getById() {
        when(movieRepository.findById(2L)).thenReturn(Optional.of(movie));
        Optional<Movie> result = movieService.getById(2L);

        assertThat(result)
                .isNotEmpty()
                .contains(movie);
        verify(movieRepository).findById(2L);
    }

    @Test
    public void getByIdWhenEmptyResultReturn() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Movie> result = movieService.getById(345L);

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository).findById(345L);
    }

    @Test
    public void getAll() {
        when(movieRepository.findAll()).thenReturn(Collections.singletonList(movie));
        List<Movie> result = movieService.getAll();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(movie);
        verify(movieRepository).findAll();
    }

    @Test
    public void getByImdbId() {
        when(movieRepository.findByImdbId(movie.getImdbId())).thenReturn(Optional.of(movie));
        Optional<Movie> result = movieService.getByImdbId(movie.getImdbId());

        assertThat(result)
                .isNotEmpty()
                .contains(movie);
        verify(movieRepository).findByImdbId(movie.getImdbId());
    }

    @Test
    public void getByImdbIdWhenEmptyResultReturn() {
        when(movieRepository.findByImdbId(anyString())).thenReturn(Optional.empty());
        Optional<Movie> result = movieService.getByImdbId("tf234566");

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository).findByImdbId("tf234566");
    }*/

    @Test
    public void deleteById() {
        when(movieRepository.removeById(2L)).thenReturn(1);
        boolean result = movieService.deleteById(movie.getId());

        assertThat(result).isTrue();
        verify(movieRepository).removeById(movie.getId());
    }

    @Test
    public void deleteByIdFailTest() {
        when(movieRepository.removeById(anyLong())).thenReturn(0);
        boolean result = movieService.deleteById(movie.getId());

        assertThat(result).isFalse();
        verify(movieRepository).removeById(anyLong());
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
}
