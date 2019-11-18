package com.garret.movies.batch;

import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.dto.OmdbApiSearchResponse;
import com.garret.movies.omdb.dto.SimpleMovie;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SimpleMovieReaderTest {

    @Mock
    private OmdbClient omdbClient;
    @InjectMocks
    private SimpleMovieReader simpleMovieReader;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void beforeStepInit() {
        String title = "film";
        SimpleMovie testMovie = new SimpleMovie();
        List<SimpleMovie> inputList = Collections.singletonList(testMovie);
        OmdbApiSearchResponse<List<SimpleMovie>> response = new OmdbApiSearchResponse<>();
        response.setData(inputList);
        response.setSuccess(true);
        when(omdbClient.searchMovies(title, 1)).thenReturn(response);
        when(omdbClient.searchMovies(title, 2)).thenReturn(new OmdbApiSearchResponse<>());

        simpleMovieReader.setTitle(title);
        simpleMovieReader.init();

        assertThat(simpleMovieReader.getMovieData())
                .isNotEmpty()
                .isEqualTo(inputList);
        verify(omdbClient, times(2)).searchMovies(eq(title), anyInt());
    }

    @Test
    public void beforeStepInitWhenResultsNotFound() {
        when(omdbClient.searchMovies(any(), eq(1))).thenReturn(new OmdbApiSearchResponse<>());

        simpleMovieReader.init();

        assertThat(simpleMovieReader.getMovieData()).isEmpty();
        verify(omdbClient).searchMovies(any(), eq(1));
    }

    @Test
    public void read() throws Exception {
        SimpleMovie testMovie = new SimpleMovie();
        List<SimpleMovie> inputList = Collections.singletonList(testMovie);
        simpleMovieReader.setMovieData(inputList);

        SimpleMovie resultMovie = simpleMovieReader.read();

        assertThat(resultMovie).isNotNull().isEqualTo(testMovie);
    }

    @Test
    public void readWhenListIsEmpty() throws Exception {
        SimpleMovie resultMovie = simpleMovieReader.read();

        assertThat(simpleMovieReader.getMovieData()).isEmpty();
        assertThat(resultMovie).isNull();
    }
}
