package com.garret.movies.service.config.mappings.impl;

import com.garret.movies.service.config.mappings.ModelMapperConfigurer;
import com.garret.movies.dao.entity.Movie;
import com.garret.movies.service.dto.response.MovieDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class MovieDtoToMovieConfigurer implements ModelMapperConfigurer {

    @Override
    public void configure(ModelMapper modelMapper) {
        PropertyMap<MovieDto, Movie> dtoToEntityMapping = new PropertyMap<MovieDto, Movie>() {
            @Override
            protected void configure() {
                map().setId(null);
            }
        };
        modelMapper.addMappings(dtoToEntityMapping);
    }
}
