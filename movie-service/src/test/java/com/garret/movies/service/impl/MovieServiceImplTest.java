package com.garret.movies.service.impl;

import com.garret.movies.dao.entity.Actor;
import com.garret.movies.dao.entity.Genre;
import com.garret.movies.dao.entity.Movie;
import com.garret.movies.dao.repository.MovieRepository;
import com.garret.movies.service.api.MovieService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MovieServiceImpl.class)
@RunWith(SpringRunner.class)
public class MovieServiceImplTest {

    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;
    private static Movie movie;

    @BeforeClass
    public static void setUp() {
        movie = new Movie();
        movie.setId(2L);
        movie.setImdbId("tt223344");
    }

    @Test
    public void save() {
        when(movieRepository.save(any())).thenReturn(movie);
        when(movieRepository.findByImdbId(anyString())).thenReturn(Optional.empty());

        Movie result = movieService.save(new Movie());

        assertThat(result)
                .isNotNull()
                .isEqualToComparingFieldByField(movie);
        assertThat(result.getId()).isEqualTo(2L);

        verify(movieRepository, times(1)).save(any(Movie.class));
        verify(movieRepository, times(1)).findByImdbId(any());
    }

    @Test
    public void saveExistedMovie() {
        when(movieRepository.findByImdbId(anyString())).thenReturn(Optional.of(movie));
        Movie result = movieService.save(movie);

        assertThat(result)
                .isNotNull()
                .isEqualToComparingFieldByField(movie);
        assertThat(result.getId()).isEqualTo(2L);

        verify(movieRepository, never()).save(movie);
        verify(movieRepository, times(1)).findByImdbId(movie.getImdbId());
    }

    @Test(expected = NullPointerException.class)
    public void save_withNullParam() {
        movieService.save(null);
    }


    @Test
    public void saveAll() {
        List<Movie> resultList = movieService.saveAll(Collections.singletonList(movie));

        assertThat(resultList)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(movie);
        verify(movieRepository, times(1)).saveAll(anyIterable());
    }

    @Test(expected = NullPointerException.class)
    public void saveAll_withNullParam() {
        movieService.saveAll(null);
    }

    @Test
    public void getById() {
        when(movieRepository.findById(2L)).thenReturn(Optional.of(movie));
        Optional<Movie> result = movieService.getById(2L);

        assertThat(result)
                .isNotEmpty()
                .contains(movie);
        verify(movieRepository, times(1)).findById(2L);
    }

    @Test
    public void getById_EmptyResult() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Movie> result = movieService.getById(345L);

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository, times(1)).findById(345L);
    }

    @Test(expected = NullPointerException.class)
    public void getById_withNullParam() {
        movieService.getById(null);
    }

    @Test
    public void getAll() {
        when(movieRepository.findAll()).thenReturn(Collections.singletonList(movie));
        List<Movie> result = movieService.getAll();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(movie);
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    public void getByImdbId() {
        when(movieRepository.findByImdbId(movie.getImdbId())).thenReturn(Optional.of(movie));
        Optional<Movie> result = movieService.getByImdbId(movie.getImdbId());

        assertThat(result)
                .isNotEmpty()
                .contains(movie);
        verify(movieRepository, times(1)).findByImdbId(movie.getImdbId());
    }

    @Test
    public void getByImdbId_EmptyResult() {
        when(movieRepository.findByImdbId(anyString())).thenReturn(Optional.empty());
        Optional<Movie> result = movieService.getByImdbId("tf234566");

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository, times(1)).findByImdbId("tf234566");
    }

    @Test(expected = NullPointerException.class)
    public void getByImdbId_withNullParam() {
        movieService.getByImdbId(null);
    }

    @Test
    public void getAllByGenre() {
        List<Genre> genres = Collections.singletonList(new Genre("Action"));
        movie.setGenres(genres);
        when(movieRepository.findAllByGenresValue("action")).thenReturn(Collections.singletonList(movie));
        List<Movie> result = movieService.getAllByGenre("action");

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .contains(movie);
        assertThat(result.get(0).getGenres()).isEqualTo(genres);
        verify(movieRepository, times(1)).findAllByGenresValue("action");
    }

    @Test
    public void getAllByGenre_EmptyResult() {
        when(movieRepository.findAllByGenresValue(anyString())).thenReturn(Collections.emptyList());
        List<Movie> result = movieService.getAllByGenre("comedy");

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository, times(1)).findAllByGenresValue("comedy");
    }

    @Test(expected = NullPointerException.class)
    public void getAllByGenre_withNullParam() {
        movieService.getAllByGenre(null);
    }

    @Test
    public void getAllByActor() {
        List<Actor> actors = Collections.singletonList(new Actor("Nikole Kidman"));
        movie.setActors(actors);
        when(movieRepository.findAllByActorsFullNameContains("nikole")).thenReturn(Collections.singletonList(movie));
        List<Movie> result = movieService.getAllByActor("nikole");

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .contains(movie);
        assertThat(result.get(0).getActors()).isEqualTo(actors);
        verify(movieRepository, times(1)).findAllByActorsFullNameContains("nikole");
    }

    @Test
    public void getAllByActor_EmptyResult() {
        when(movieRepository.findAllByActorsFullNameContains(anyString())).thenReturn(Collections.emptyList());
        List<Movie> result = movieService.getAllByActor("nikole");

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository, times(1)).findAllByActorsFullNameContains("nikole");
    }

    @Test(expected = NullPointerException.class)
    public void getAllByActor_withNullParam() {
        movieService.getAllByActor(null);
    }

    @Test
    public void getAllByYear() {
        movie.setReleased(Date.valueOf("2015-02-12"));
        when(movieRepository.findAllByReleasedBetween(Date.valueOf("2015-01-01"), Date.valueOf("2015-12-31")))
                .thenReturn(Collections.singletonList(movie));
        List<Movie> result = movieService.getAllByYear(2015);

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .contains(movie);
        assertThat(result.get(0).getReleased()).isEqualTo("2015-02-12");
        verify(movieRepository, times(1))
                .findAllByReleasedBetween(Date.valueOf("2015-01-01"), Date.valueOf("2015-12-31"));
    }

    @Test
    public void getAllByYear_EmptyResult() {
        when(movieRepository.findAllByReleasedBetween(any(Date.class), any(Date.class)))
                .thenReturn(Collections.emptyList());
        List<Movie> result = movieService.getAllByYear(5555);

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository, times(1))
                .findAllByReleasedBetween(Date.valueOf("5555-01-01"), Date.valueOf("5555-12-31"));
    }

    @Test(expected = NullPointerException.class)
    public void getAllByYear_withNullParam() {
        movieService.getAllByYear(null);
    }

    /*@Test
    public void getAllByLanguage() {
    }

    @Test
    public void getTopByVotes() {
    }

    @Test
    public void getTopByRating() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void existsInDb() {
    }

    @Test
    public void getAllByCountry() {
    }*/
}