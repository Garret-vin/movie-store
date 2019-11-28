package com.garret.movies.common.config.mapper.impl;

import com.garret.movies.common.config.mapper.ModelMapperConfigurer;
import com.garret.movies.dao.entity.Genre;
import com.garret.movies.service.dto.GenreDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreMapperConfigurer implements ModelMapperConfigurer {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(GenreDto.class, Genre.class)
                .addMappings(mapper -> mapper.map(GenreDto::getValue, Genre::setName));

        modelMapper.createTypeMap(Genre.class, GenreDto.class)
                .addMappings(mapper -> mapper.map(Genre::getName, GenreDto::setValue));
    }
}
