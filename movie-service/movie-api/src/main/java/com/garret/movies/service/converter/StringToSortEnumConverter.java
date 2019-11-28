package com.garret.movies.service.converter;

import com.garret.movies.service.dto.criteria.SortOptions;
import org.springframework.core.convert.converter.Converter;

public class StringToSortEnumConverter implements Converter<String, SortOptions> {
    @Override
    public SortOptions convert(String s) {
        try {
            return SortOptions.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
