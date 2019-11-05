package com.garret.movies.service.config.mappings.impl;

import com.garret.movies.service.config.mappings.ModelMapperConfigurer;
import com.garret.movies.dao.entity.Actor;
import com.garret.movies.service.dto.ActorDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ActorDtoToActorConfigurer implements ModelMapperConfigurer {

    @Override
    public void configure(ModelMapper modelMapper) {
        PropertyMap<ActorDto, Actor> dtoToEntityMapping = new PropertyMap<ActorDto, Actor>() {
            @Override
            protected void configure() {
                map().setFullName(source.getValue());
            }
        };
        modelMapper.addMappings(dtoToEntityMapping);
    }
}
