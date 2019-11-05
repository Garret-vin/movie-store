package com.garret.movies.service.config.mappings.impl;

import com.garret.movies.service.config.mappings.ModelMapperConfigurer;
import com.garret.movies.dao.entity.Actor;
import com.garret.movies.service.dto.ActorDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ActorToActorDtoConfigurer implements ModelMapperConfigurer {

    @Override
    public void configure(ModelMapper modelMapper) {
        PropertyMap<Actor, ActorDto> dtoToEntityMapping = new PropertyMap<Actor, ActorDto>() {
            @Override
            protected void configure() {
                map().setValue(source.getFullName());
            }
        };
        modelMapper.addMappings(dtoToEntityMapping);
    }
}
