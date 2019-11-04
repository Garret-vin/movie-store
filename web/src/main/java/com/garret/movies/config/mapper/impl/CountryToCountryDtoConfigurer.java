package com.garret.movies.config.mapper.impl;

import com.garret.movies.config.mapper.ModelMapperConfigurer;
import com.garret.movies.dao.entity.Country;
import com.garret.movies.service.dto.CountryDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class CountryToCountryDtoConfigurer implements ModelMapperConfigurer {

    @Override
    public void configure(ModelMapper modelMapper) {
        PropertyMap<Country, CountryDto> dtoToEntityMapping = new PropertyMap<Country, CountryDto>() {
            @Override
            protected void configure() {
                map().setValue(source.getName());
            }
        };
        modelMapper.addMappings(dtoToEntityMapping);
    }
}
