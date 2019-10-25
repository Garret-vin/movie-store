package com.garret.movies.batch;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.omdb.dto.OmdbMovie;
import com.garret.movies.omdb.dto.ShortMovie;
import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.service.api.MovieService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ShortMovieToOmdbMovieProcessorTest {

    private OmdbClient omdbClient = mock(OmdbClient.class);
    private MovieService movieService = mock(MovieService.class);
    private ShortMovieToOmdbMovieProcessor processor = new ShortMovieToOmdbMovieProcessor(omdbClient, movieService);
    private ShortMovie testShortMovie;

    @Before
    public void setUp() {
        testShortMovie = new ShortMovie();
        testShortMovie.setImdbId("22");
    }
    @Test
    public void process() throws Exception {
        OmdbMovie movie = new OmdbMovie();
        movie.setImdbId(testShortMovie.getImdbId());
        when(movieService.existsByImdbId(testShortMovie.getImdbId())).thenReturn(false);
        when(omdbClient.searchByImdbId(testShortMovie.getImdbId())).thenReturn(movie);
        OmdbMovie result = processor.process(testShortMovie);

        assertThat(result).isNotNull().isEqualTo(movie);
        verify(movieService).existsByImdbId(testShortMovie.getImdbId());
        verify(omdbClient).searchByImdbId(testShortMovie.getImdbId());
    }

    @Test
    public void processExistedMovie() throws Exception {
        Movie movie = new Movie();
        movie.setImdbId(testShortMovie.getImdbId());
        when(movieService.existsByImdbId(testShortMovie.getImdbId())).thenReturn(true);
        OmdbMovie result = processor.process(testShortMovie);

        assertThat(result).isNull();
        verify(movieService).existsByImdbId(testShortMovie.getImdbId());
        verify(omdbClient, never()).searchByImdbId(testShortMovie.getImdbId());
    }
}