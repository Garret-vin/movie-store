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
public class ActorToActorDtoConfigurer implements ModelMapperConfigurer {

    private ModelMapper modelMapper;

    @Override
    public void configure() {
        PropertyMap<Actor, ActorDto> dtoToEntityMapping = new PropertyMap<Actor, ActorDto>() {
            @Override
            protected void configure() {
                map().setValue(source.getFullName());
            }
        };
        modelMapper.addMappings(dtoToEntityMapping);
    }
}
