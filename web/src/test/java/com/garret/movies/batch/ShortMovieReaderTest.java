package com.garret.movies.batch;

import com.garret.movies.omdb.dto.ShortMovie;
import com.garret.movies.omdb.api.OmdbClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ShortMovieReaderTest {

    @Mock
    private OmdbClient omdbClient;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void read() throws Exception {
        ShortMovie testMovie = new ShortMovie();
        List<ShortMovie> inputList = Collections.singletonList(testMovie);
        when(omdbClient.searchMovies(any())).thenReturn(inputList);
        ShortMovieReader shortMovieReader = new ShortMovieReader(omdbClient);
        shortMovieReader.init();
        ShortMovie resultMovie = shortMovieReader.read();

        assertThat(resultMovie).isNotNull().isEqualTo(testMovie);

        verify(omdbClient).searchMovies(any());
    }

    @Test
    public void readWhenListIsEmpty() throws Exception {
        when(omdbClient.searchMovies(any())).thenReturn(Collections.emptyList());
        ShortMovieReader shortMovieReader = new ShortMovieReader(omdbClient);
        shortMovieReader.init();
        ShortMovie resultMovie = shortMovieReader.read();

        assertThat(resultMovie).isNull();

        verify(omdbClient).searchMovies(any());
    }
}
