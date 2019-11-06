package com.garret.movies.omdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MoviesResponse<T> {
    @JsonProperty("Search")
    private List<T> contentList;
    @JsonProperty("totalResults")
    private int totalResults;
    @JsonProperty("Response")
    private boolean response;
}
