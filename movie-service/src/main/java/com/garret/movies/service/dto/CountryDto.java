package com.garret.movies.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.garret.movies.service.dto.marker.Valuable;
import lombok.Data;

@Data
public class CountryDto implements Valuable {
    @JsonProperty("country")
    private String value;
}
