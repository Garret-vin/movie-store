package com.garret.movies.config.mapper.impl;

import com.garret.movies.config.mapper.ModelMapperConfigurer;
import com.garret.movies.dao.entity.Movie;
import com.garret.movies.service.dto.MovieDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovieDtoToMovieConfigurer implements ModelMapperConfigurer {

    private ModelMapper modelMapper;

    @Override
    public void configure() {
        PropertyMap<MovieDto, Movie> dtoToEntityMapping = new PropertyMap<MovieDto, Movie>() {
            @Override
            protected void configure() {
                map().setId(null);
            }
        };
        modelMapper.addMappings(dtoToEntityMapping);
    }
}
