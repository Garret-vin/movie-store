package com.garret.movies.common.config.mapper.impl;

import com.garret.movies.common.config.mapper.ModelMapperConfigurer;
import com.garret.movies.dao.entity.Movie;
import com.garret.movies.service.dto.response.MovieDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MovieDtoToMovieConfigurer implements ModelMapperConfigurer {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(MovieDto.class, Movie.class)
                .addMappings(mapper -> {
                    mapper.skip(Movie::setId);
                });
    }
}
