package com.garret.movies.service.impl;

import com.garret.movies.dao.entity.Actor;
import com.garret.movies.dao.entity.Genre;
import com.garret.movies.dao.entity.Language;
import com.garret.movies.dao.entity.Movie;
import com.garret.movies.dao.repository.MovieRepository;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceImplTest {

    private MovieRepository movieRepository = mock(MovieRepository.class);
    private MovieServiceImpl movieService = new MovieServiceImpl(movieRepository);
    private static Movie movie;
    private static final String GENRE = "action";
    private static final String ACTOR = "nikole";
    private static final String LANGUAGE = "english";
    private static final String COUNTRY = "usa";

    @BeforeClass
    public static void setUp() {
        movie = new Movie();
        movie.setId(2L);
        movie.setImdbId("tt223344");
        movie.setCountry(Collections.singletonList("USA"));
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
    }

    @Test
    public void getAllByGenre() {
        when(movieRepository.findAllByGenresValue(GENRE)).thenReturn(Collections.singletonList(movie));
        List<Movie> result = movieService.getAllByGenre(GENRE);

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .contains(movie);
        assertThat(result.get(0).getGenres()).isEqualTo(movie.getGenres());
        verify(movieRepository).findAllByGenresValue(GENRE);
    }

    @Test
    public void getAllByGenreWhenEmptyResultReturn() {
        when(movieRepository.findAllByGenresValue(GENRE)).thenReturn(Collections.emptyList());
        List<Movie> result = movieService.getAllByGenre(GENRE);

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository).findAllByGenresValue(GENRE);
    }

    @Test
    public void getAllByActor() {
        when(movieRepository.findAllByActorsFullNameContains(ACTOR)).thenReturn(Collections.singletonList(movie));
        List<Movie> result = movieService.getAllByActor(ACTOR);

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .contains(movie);
        assertThat(result.get(0).getActors()).isEqualTo(movie.getActors());
        verify(movieRepository).findAllByActorsFullNameContains(ACTOR);
    }

    @Test
    public void getAllByActorwhenEmptyResultReturn() {
        when(movieRepository.findAllByActorsFullNameContains(anyString())).thenReturn(Collections.emptyList());
        List<Movie> result = movieService.getAllByActor(ACTOR);

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository).findAllByActorsFullNameContains(eq(ACTOR));
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
        verify(movieRepository)
                .findAllByReleasedBetween(Date.valueOf("2015-01-01"), Date.valueOf("2015-12-31"));
    }

    @Test
    public void getAllByYearWhenEmptyResultReturn() {
        when(movieRepository.findAllByReleasedBetween(any(Date.class), any(Date.class)))
                .thenReturn(Collections.emptyList());
        List<Movie> result = movieService.getAllByYear(5555);

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository)
                .findAllByReleasedBetween(Date.valueOf("5555-01-01"), Date.valueOf("5555-12-31"));
    }

    @Test
    public void getAllByLanguage() {
        when(movieRepository.findAllByLanguagesValue(LANGUAGE)).thenReturn(Collections.singletonList(movie));
        List<Movie> result = movieService.getAllByLanguage(LANGUAGE);

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(movie);
        verify(movieRepository).findAllByLanguagesValue(eq(LANGUAGE));
    }

    @Test
    public void getAllByLanguageWhenEmptyResultReturn() {
        when(movieRepository.findAllByLanguagesValue(anyString())).thenReturn(Collections.emptyList());
        List<Movie> result = movieService.getAllByLanguage(LANGUAGE);

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository).findAllByLanguagesValue(eq(LANGUAGE));
    }

    @Test
    public void getAllByCountry() {
        when(movieRepository.findAllByCountry(COUNTRY)).thenReturn(Collections.singletonList(movie));
        List<Movie> result = movieService.getAllByCountry(COUNTRY);

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(movie);
        verify(movieRepository).findAllByCountry(eq(COUNTRY));
    }

    @Test
    public void getAllByCountryWhenEmptyResultReturn() {
        when(movieRepository.findAllByCountry(COUNTRY)).thenReturn(Collections.emptyList());
        List<Movie> result = movieService.getAllByCountry(COUNTRY);

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository).findAllByCountry(eq(COUNTRY));
    }

    @Test
    public void getTopByVotes() {
        when(movieRepository.findAllByOrderByImdbVotesDesc()).thenReturn(Collections.singletonList(movie));
        List<Movie> result = movieService.getTopByVotes();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(movie);
        verify(movieRepository).findAllByOrderByImdbVotesDesc();
    }

    @Test
    public void getTopByVotesWhenEmptyResultReturn() {
        when(movieRepository.findAllByOrderByImdbVotesDesc()).thenReturn(Collections.emptyList());
        List<Movie> result = movieService.getTopByVotes();

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository).findAllByOrderByImdbVotesDesc();
    }

    @Test
    public void getTopByRating() {
        when(movieRepository.findAllByOrderByImdbRatingDesc()).thenReturn(Collections.singletonList(movie));
        List<Movie> result = movieService.getTopByRating();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(movie);
        verify(movieRepository).findAllByOrderByImdbRatingDesc();
    }

    @Test
    public void getTopByRatingWhenEmptyResultReturn() {
        when(movieRepository.findAllByOrderByImdbRatingDesc()).thenReturn(Collections.emptyList());
        List<Movie> result = movieService.getTopByRating();

        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(movieRepository).findAllByOrderByImdbRatingDesc();
    }

    @Test
    public void deleteById() {
        when(movieRepository.existsById(2L)).thenReturn(true);
        boolean result = movieService.deleteById(movie.getId());

        assertThat(result).isTrue();
        verify(movieRepository).existsById(movie.getId());
        verify(movieRepository).deleteById(movie.getId());
    }

    @Test
    public void deleteByIdFailTest() {
        when(movieRepository.existsById(anyLong())).thenReturn(false);
        boolean result = movieService.deleteById(movie.getId());

        assertThat(result).isFalse();
        verify(movieRepository).existsById(movie.getId());
        verify(movieRepository, never()).deleteById(anyLong());
    }

    @Test
    public void existsInDb() {
        when(movieRepository.existsByImdbId(movie.getImdbId())).thenReturn(true);
        boolean result = movieService.existsInDb(movie);

        assertThat(result).isTrue();
        verify(movieRepository).existsByImdbId(movie.getImdbId());
    }

    @Test
    public void existsInDbFailTest() {
        when(movieRepository.existsByImdbId(movie.getImdbId())).thenReturn(false);
        boolean result = movieService.existsInDb(movie);

        assertThat(result).isFalse();
        verify(movieRepository).existsByImdbId(movie.getImdbId());
    }
}