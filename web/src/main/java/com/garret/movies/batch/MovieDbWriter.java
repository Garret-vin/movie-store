package com.garret.movies.batch;

import com.garret.movies.omdb.entity.OmdbMovie;
import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.dto.response.MovieDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
@StepScope
@RequiredArgsConstructor
public class MovieDbWriter implements ItemWriter<OmdbMovie> {

    private final MovieService movieService;
    private final ModelMapper modelMapper;
    private static final Type MOVIE_DTO_LIST_TYPE = new TypeToken<List<MovieDto>>() {
    }.getType();

    @Override
    public void write(List<? extends OmdbMovie> list) throws Exception {
        List<MovieDto> dtoList = modelMapper.map(list, MOVIE_DTO_LIST_TYPE);
        movieService.saveAll(dtoList);
    }
}
