package com.garret.movies.service.dto;

import com.garret.movies.service.dto.enums.MovieTypeDto;
import lombok.Data;

import java.sql.Date;

@Data
public class SimpleMovieDto {

    private String title;
    private Date released;
    private Double imdbRating;
    private MovieTypeDto type;
    private Long id;
}
