package com.garret.movies.common.config.mapper.impl;

import com.garret.movies.common.config.mapper.ModelMapperConfigurer;
import com.garret.movies.dao.entity.Country;
import com.garret.movies.service.dto.CountryDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CountryMapperConfigurer implements ModelMapperConfigurer {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(CountryDto.class, Country.class)
                .addMappings(mapper -> mapper.map(CountryDto::getValue, Country::setName));

        modelMapper.createTypeMap(Country.class, CountryDto.class)
                .addMappings(mapper -> mapper.map(Country::getName, CountryDto::setValue));
    }
}
