package com.garret.movies.service.dto;

import com.garret.movies.service.dto.marker.Valuable;
import lombok.Data;

@Data
public class GenreDto implements Valuable {
    private String value;
}
