package com.garret.movies.service.config.mappings;

import org.modelmapper.ModelMapper;

public interface ModelMapperConfigurer {
    void configure(ModelMapper modelMapper);
}
