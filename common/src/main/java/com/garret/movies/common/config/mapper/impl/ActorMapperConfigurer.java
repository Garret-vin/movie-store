package com.garret.movies.common.config.mapper.impl;

import com.garret.movies.common.config.mapper.ModelMapperConfigurer;
import com.garret.movies.dao.entity.Actor;
import com.garret.movies.service.dto.ActorDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ActorMapperConfigurer implements ModelMapperConfigurer {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(ActorDto.class, Actor.class)
                .addMappings(mapper -> mapper.map(ActorDto::getValue, Actor::setFullName));

        modelMapper.createTypeMap(Actor.class, ActorDto.class)
                .addMappings(mapper -> mapper.map(Actor::getFullName, ActorDto::setValue));
    }
}
