package com.garret.movies.service.dto;

import com.garret.movies.service.dto.marker.Valuable;
import lombok.Data;

@Data
public class CountryDto implements Valuable {
    private String name;

    @Override
    public void setValue(String value) {
        setName(value);
    }
}
