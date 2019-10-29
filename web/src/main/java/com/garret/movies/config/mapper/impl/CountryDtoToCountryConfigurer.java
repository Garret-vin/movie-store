package com.garret.movies.config.mapper.impl;

import com.garret.movies.config.mapper.ModelMapperConfigurer;
import com.garret.movies.dao.entity.Country;
import com.garret.movies.service.dto.CountryDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CountryDtoToCountryConfigurer implements ModelMapperConfigurer {

    private ModelMapper modelMapper;

    @Override
    public void configure() {
        PropertyMap<CountryDto, Country> dtoToEntityMapping = new PropertyMap<CountryDto, Country>() {
            @Override
            protected void configure() {
                map().setName(source.getValue());
            }
        };
        modelMapper.addMappings(dtoToEntityMapping);
    }
}
