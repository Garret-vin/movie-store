package com.garret.movies.config.mapper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@AllArgsConstructor
public class ModelMapperConfigurerBPP implements BeanPostProcessor {

    private List<ModelMapperConfigurer> configurerList;

    @PostConstruct
    public void configModelMapper() {
        for (ModelMapperConfigurer configurer : configurerList) {
            configurer.configure();
        }
    }
}
