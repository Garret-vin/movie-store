package com.garret.movies.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.garret.movies.service.dto.enums.GenreTypeDto;
import com.garret.movies.service.dto.marker.Valuable;
import lombok.Data;

@Data
public class GenreDto implements Valuable {
    @JsonProperty("genre")
    private GenreTypeDto value;

    @Override
    public void setValue(String value) {
        this.value = GenreTypeDto.valueOf(value);
    }
}
