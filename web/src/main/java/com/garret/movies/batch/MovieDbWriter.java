package com.garret.movies.batch;

import com.garret.movies.omdb.entity.OmdbMovie;
import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.dto.MovieDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
@StepScope
@AllArgsConstructor
public class MovieDbWriter implements ItemWriter<OmdbMovie> {

    private final MovieService movieService;
    private ModelMapper modelMapper;

    @Override
    public void write(List<? extends OmdbMovie> list) throws Exception {
        List<OmdbMovie> omdbMovieList = new ArrayList<>(list);
        Type listType = new TypeToken<List<MovieDto>>() {
        }.getType();
        List<MovieDto> dtoList = modelMapper.map(omdbMovieList, listType);
        movieService.saveAll(dtoList);
    }
}
