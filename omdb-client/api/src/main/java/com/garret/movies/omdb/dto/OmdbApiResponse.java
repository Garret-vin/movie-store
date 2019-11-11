package com.garret.movies.omdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

@Data
public class OmdbApiResponse<T> {

    @JsonUnwrapped
    @JsonProperty("Search")
    private T data;
    @JsonProperty("Response")
    private boolean success;
}
