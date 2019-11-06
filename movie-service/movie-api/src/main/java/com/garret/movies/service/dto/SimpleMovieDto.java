package com.garret.movies.service.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class SimpleMovieDto {

    private String title;
    private Date released;
    private Double imdbRating;
    private String type;
    private Long id;
}
