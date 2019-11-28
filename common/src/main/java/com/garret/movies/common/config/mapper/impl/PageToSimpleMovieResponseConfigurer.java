package com.garret.movies.common.config.mapper.impl;

import com.garret.movies.common.config.mapper.ModelMapperConfigurer;
import com.garret.movies.service.dto.response.SimpleMoviesResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
public class PageToSimpleMovieResponseConfigurer implements ModelMapperConfigurer {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(PageImpl.class, SimpleMoviesResponse.class)
                .addMappings(mapper -> {
                    mapper.map(PageImpl::getTotalElements, SimpleMoviesResponse::setTotalElements);
                    mapper.map(PageImpl::getSize, SimpleMoviesResponse::setPageSize);
                    mapper.map(PageImpl::getContent, SimpleMoviesResponse::setSearchContent);
                });
    }
}
