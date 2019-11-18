package com.garret.movies.batch;

import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.dto.OmdbApiSearchResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    public void doReadPage() {
        when(omdbClient.searchMovies(any(), anyInt())).thenReturn(new OmdbApiSearchResponse<>());

        simpleMovieReader.doReadPage();

        verify(omdbClient).searchMovies(any(), anyInt());
    }
}
