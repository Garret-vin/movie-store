package com.garret.movies.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.garret.movies.service.dto.marker.Valuable;
import lombok.Data;

@Data
public class GenreDto implements Valuable {
    @JsonProperty("genre")
    private String value;
}
