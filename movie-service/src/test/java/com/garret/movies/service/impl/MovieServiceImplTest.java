package com.garret.movies.service.impl;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.dao.repository.MovieRepository;
import org.jooq.DSLContext;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceImplTest {

    private DSLContext jooq = mock(DSLContext.class);
    private MovieRepository movieRepository = mock(MovieRepository.class);
    private MovieServiceImpl movieService = new MovieServiceImpl(movieRepository, jooq);
    private static Movie movie;

    @BeforeClass
    public static void setUp() {
        movie = new Movie();
        movie.setId(2L);
        movie.setImdbId("tt223344");
    }

    @Test
    public void save() {
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        when(movieRepository.findByImdbId(anyString())).thenReturn(Optional.empty());

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
