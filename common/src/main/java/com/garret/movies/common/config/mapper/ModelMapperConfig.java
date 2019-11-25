package com.garret.movies.common.config.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

    private final List<ModelMapperConfigurer> configurerList;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(PRIVATE)
                .setSkipNullEnabled(true)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);

        configurerList.forEach(configurer -> configurer.configure(modelMapper));
        return modelMapper;
    }
}
