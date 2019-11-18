package com.garret.movies.batch;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.dto.OmdbApiResponse;
import com.garret.movies.omdb.dto.OmdbMovie;
import com.garret.movies.omdb.dto.SimpleMovie;
import com.garret.movies.service.api.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SimpleMovieToOmdbMovieProcessorTest {

    @Mock
    private OmdbClient omdbClient;
    @Mock
    private MovieService movieService;
    @InjectMocks
    private SimpleMovieToOmdbMovieProcessor processor;
    private SimpleMovie testShortMovie;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testShortMovie = new SimpleMovie();
        testShortMovie.setImdbId("22");
    }

    @Test
    public void process() throws Exception {
        OmdbMovie omdbMovie = new OmdbMovie();
        omdbMovie.setImdbId(testShortMovie.getImdbId());
        OmdbApiResponse<OmdbMovie> response = new OmdbApiResponse<>();
        response.setData(omdbMovie);
        response.setSuccess(true);
        when(movieService.existsByImdbId(testShortMovie.getImdbId())).thenReturn(false);
        when(omdbClient.searchByImdbId(testShortMovie.getImdbId())).thenReturn(response);

        OmdbMovie result = processor.process(testShortMovie);

        assertThat(result).isNotNull().isEqualTo(omdbMovie);
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
        verify(omdbClient, never()).searchByImdbId(any());
    }
}
