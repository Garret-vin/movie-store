package com.garret.movies.batch;

import com.garret.movies.omdb.entity.OmdbMovie;
import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.dto.MovieDto;
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

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieDbWriterTest {

    @Mock
    private MovieService movieService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private MovieDbWriter writer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void write() throws Exception {
        OmdbMovie omdbMovie = new OmdbMovie();
        List<OmdbMovie> omdbMovieList = Collections.singletonList(omdbMovie);
        MovieDto movieDto = new MovieDto();
        List<MovieDto> movieDtoList = Collections.singletonList(movieDto);
        Type listType = new TypeToken<List<MovieDto>>() {
        }.getType();

        when(modelMapper.map(anyList(), eq(listType))).thenReturn(movieDtoList);
        when(movieService.saveAll(movieDtoList)).thenReturn(movieDtoList);

        writer.write(omdbMovieList);

        verify(modelMapper).map(anyList(), eq(listType));
        verify(movieService).saveAll(movieDtoList);
    }
}