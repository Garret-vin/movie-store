package com.garret.movies.common.config.mapper.impl;

import com.garret.movies.common.config.mapper.ModelMapperConfigurer;
import com.garret.movies.dao.entity.Language;
import com.garret.movies.service.dto.LanguageDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapperConfigurer implements ModelMapperConfigurer {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(LanguageDto.class, Language.class)
                .addMappings(mapper -> mapper.map(LanguageDto::getValue, Language::setName));

        modelMapper.createTypeMap(Language.class, LanguageDto.class)
                .addMappings(mapper -> mapper.map(Language::getName, LanguageDto::setValue));
    }
}
