package com.garret.movies.service.dto.response;

import com.garret.movies.service.dto.SimpleMovieDto;
import lombok.Data;

import java.util.List;

@Data
public class SimpleMoviesResponse {

    private List<SimpleMovieDto> searchContent;
    private int pageSize;
    private long totalElements;
}
