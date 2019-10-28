package com.garret.movies.omdb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MoviesResponse {
    @JsonProperty("Search")
    private List<ShortMovie> shortMovieList;
    @JsonProperty("totalResults")
    private String totalResults;
    @JsonProperty("Response")
    private String response;
}
