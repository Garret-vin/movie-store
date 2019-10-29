package com.garret.movies.service.dto;

import com.garret.movies.service.dto.marker.Valuable;
import lombok.Data;

@Data
public class ActorDto implements Valuable {
    private String fullName;

    @Override
    public void setValue(String value) {
        setFullName(value);
    }
}
