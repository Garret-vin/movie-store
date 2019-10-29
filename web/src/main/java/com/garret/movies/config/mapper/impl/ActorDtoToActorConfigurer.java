package com.garret.movies.config.mapper.impl;

import com.garret.movies.config.mapper.ModelMapperConfigurer;
import com.garret.movies.dao.entity.Actor;
import com.garret.movies.service.dto.ActorDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActorDtoToActorConfigurer implements ModelMapperConfigurer {

    private ModelMapper modelMapper;

    @Override
    public void configure() {
        PropertyMap<ActorDto, Actor> dtoToEntityMapping = new PropertyMap<ActorDto, Actor>() {
            @Override
            protected void configure() {
                map().setFullName(source.getValue());
            }
        };
        modelMapper.addMappings(dtoToEntityMapping);
    }
}
