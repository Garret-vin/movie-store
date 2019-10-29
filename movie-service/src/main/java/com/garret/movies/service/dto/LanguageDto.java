package com.garret.movies.service.dto;

import com.garret.movies.service.dto.marker.Valuable;
import lombok.Data;

@Data
public class LanguageDto implements Valuable {
    private String value;
}
